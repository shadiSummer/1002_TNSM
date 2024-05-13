#!/usr/bin/python
"""
Test to validate MPTCP operation across at least two links.
"""

import sys
from subprocess import Popen, PIPE
from time import sleep
import argparse

from mininet.net import Mininet, CLI
from mininet.log import lg
from mininet.node import OVSKernelSwitch as Switch
from mininet.link import Link, TCLink
from mininet.util import makeNumeric, custom
from mininet.node import Controller
from topo import TwoHostNInterfaceTopo
import time
#import subprocess


#Remote

# client to server function
def start_client(node, filename):
    node.cmd('cd /var/www/html')
#    node.cmd('xterm -e java init.Main %s &' % filename)


def start_server(node):
    node.cmd('cd /var/www/html')
#    node.cmd('xterm -e java server.SimpleHttpServer &')


def id2mac(h):
    if h >= 2:
        a = hex(h)
        b = a[2:]
        return b
    else:
        return h


def mptcp_run(n, m, net):

    h1 = net.getNodeByName('h1')  # client
    h2 = net.getNodeByName('h2')
    h3 = net.getNodeByName('h3')
    h4 = net.getNodeByName('h4')
    h5 = net.getNodeByName('h5')
    
    

    h100 = net.getNodeByName('h100')  # videoServer
    

    h100.cmd('xterm -e python -m SimpleHTTPServer 8080 &')
    h1.cmd('xterm -e google-chrome --enable-logging --no-sandbox --user-data-dir=./log1/ --app=http://10.0.0.100:8080/dash.js/samples/dash-if-reference-player/index.html &')
    h2.cmd('xterm -e google-chrome --enable-logging --no-sandbox --user-data-dir=./log2/ --app=http://10.0.0.100:8080/dash.js/samples/dash-if-reference-player/index.html &')
    h3.cmd('xterm -e google-chrome --enable-logging --no-sandbox --user-data-dir=./log3/ --app=http://10.0.0.100:8080/dash.js/samples/dash-if-reference-player/index.html &')
    h4.cmd('xterm -e google-chrome --enable-logging --no-sandbox --user-data-dir=./log4/ --app=http://10.0.0.100:8080/dash.js/samples/dash-if-reference-player/index.html &')
    h5.cmd('xterm -e google-chrome --enable-logging --no-sandbox --user-data-dir=./log5/ --app=http://10.0.0.100:8080/dash.js/samples/dash-if-reference-player/index.html &')



    HOSTS = [h1, h2, h3, h4, h5]
    for h in HOSTS:
        c = HOSTS.index(h) + 1
        for i in range(n):
            cc = id2mac(c)
            h.cmdPrint(
                'ifconfig %s-eth%i hw ether 00:00:00:00:00:%i%s 10.0.%i.%i netmask 255.255.255.0' % (h, i, i, cc, i, c))
            dev = '%s-eth%i' % (h, i)
            table = '%s' % (i + 1)
            h.cmdPrint('ip rule add from 10.0.%i.%i table %s' % (i, c, table))
            h.cmdPrint('ip route add 10.0.%i.0/24 dev %s scope link table %s' % (i, dev, table))
            h.cmdPrint('ip route add default via 10.0.%i.%i dev %s table %s' % (i, c, dev, table))

            # arp
        for i in range(n):
            h.cmdPrint('arp -s -i %s-eth%i 10.0.0.100 00:00:00:00:00:64' % (h, i))
            

            h100.cmdPrint('arp -s -i h100-eth0 10.0.%i.%i 00:00:00:00:00:%i%s' % (i, c, i, cc))
            

    return None



def startProcess(net):
    h1 = net.getNodeByName('h1')  # client
    h2 = net.getNodeByName('h2')
    h3 = net.getNodeByName('h3')
    h4 = net.getNodeByName('h4')
    h5 = net.getNodeByName('h5')
    
    
    h100 = net.getNodeByName('h100')  # trackerServer

    start_server(h100)
    time.sleep(3)
    start_client(h1, '1')  # gold
    start_client(h2, '2')
    start_client(h3, '3')
    start_client(h4, '4')
    start_client(h5, '5')
    




def genericTest(n, m, topo, mptcp_run):
    link = custom(TCLink)
    net = Mininet(topo=topo, switch=Switch, link=link, controller=Controller)  #Remote
    net.addController('c0')
    net.start()
    data = mptcp_run(n, m, net)
    time.sleep(2)
    startProcess(net)
    CLI(net)
    net.stop()


def main():
    n = 3
    m = 3
    topo = TwoHostNInterfaceTopo(n)
    genericTest(n, m, topo, mptcp_run)


if __name__ == '__main__':
    main()
#    subprocess.call(['./limit_thesis.sh'])
