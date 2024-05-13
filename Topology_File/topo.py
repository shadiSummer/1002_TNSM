#!/usr/bin/python

from mininet.topo import Topo
import subprocess

class TwoHostNInterfaceTopo(Topo):
    "Two hosts connected by N interfaces"

    def __init__(self, n, **opts):
        "n is the number of interfaces connecting the hosts."
        super(TwoHostNInterfaceTopo, self).__init__(**opts)

        s1 = self.addSwitch('s1')
        s2 = self.addSwitch('s2')
        s3 = self.addSwitch('s3')
        s4 = self.addSwitch('s4')
        s5 = self.addSwitch('s5')
        s6 = self.addSwitch('s6')
        s7 = self.addSwitch('s7')
        s8 = self.addSwitch('s8')
        s9 = self.addSwitch('s9')
        s10 = self.addSwitch('s10')
        s11 = self.addSwitch('s11')
        
        
        # client connections
        h1 = self.addHost('h1', mac='00:00:00:00:00:01', ip='10.0.0.1')
        
        
        # Server connections
        h100 = self.addHost('h100', mac='00:00:00:00:00:64', ip='10.0.0.100')
        
        
        # Network switch connections
        self.addLink(s1, s2, bw=1, delay='50ms', loss=0, use_htb=True)
        self.addLink(s1, s10, bw=1, delay='55ms', loss=0, use_htb=True)
        self.addLink(s2, s3, bw=1, delay='60ms', loss=0, use_htb=True)
        self.addLink(s2, s11, bw=1, delay='65ms', loss=0, use_htb=True)
        self.addLink(s3, s4, bw=1, delay='70ms', loss=0, use_htb=True)
        self.addLink(s4, s5, bw=1, delay='75ms', loss=0, use_htb=True)
        self.addLink(s4, s11, bw=1, delay='80ms', loss=0, use_htb=True)
        self.addLink(s5, s6, bw=1, delay='85ms', loss=0, use_htb=True)
        self.addLink(s6, s7, bw=1, delay='90ms', loss=0, use_htb=True)
        self.addLink(s7, s8, bw=1, delay='95ms', loss=0, use_htb=True)
        self.addLink(s7, s11, bw=1, delay='100ms', loss=0, use_htb=True)
        self.addLink(s8, s9, bw=1, delay='50ms', loss=0, use_htb=True)
        self.addLink(s9, s10, bw=1, delay='55ms', loss=0, use_htb=True)
        self.addLink(s10, s11, bw=1, delay='60ms', loss=0, use_htb=True)

        

        # Client to switch connections

                # clients 1-15 to switches 1-2-3

        # Gold Clients
        self.addLink(h1, s1, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h1, s2, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h1, s3, bw=15, delay='0ms', loss=0, use_htb=True)

        self.addLink(h2, s1, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h2, s2, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h2, s3, bw=15, delay='0ms', loss=0, use_htb=True)
        
        self.addLink(h3, s1, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h3, s2, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h3, s3, bw=15, delay='0ms', loss=0, use_htb=True)
        
        self.addLink(h4, s1, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h4, s2, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h4, s3, bw=15, delay='0ms', loss=0, use_htb=True)
        
        self.addLink(h5, s1, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h5, s2, bw=15, delay='0ms', loss=0, use_htb=True)
        self.addLink(h5, s3, bw=15, delay='0ms', loss=0, use_htb=True)

        
        
        # Server to switch connections
        self.addLink(h100, s5, bw=100, delay='0ms', loss=0, use_htb=True)
        self.addLink(h100, s7, bw=100, delay='0ms', loss=0, use_htb=True)
        self.addLink(h100, s9, bw=100, delay='0ms', loss=0, use_htb=True)

        
#        subprocess.call(['./limit_thesis.sh'])

topos = {'2hostNintf': lambda n: TwoHostNInterfaceTopo(n)}
