# 1002_TNSM
This repository is created for all the developments made during the TUBITAK 1002 project.

# Genetic Algorithm for Optimal Path Selection

This Java program demonstrates the use of a genetic algorithm to solve a path selection in a network with an MPTCP transport protocol optimization problem. The goal is to find the fittest chromosome in a population by evolving and evaluating the individuals over multiple generations.

## Problem Description

This project implements a genetic algorithm (GA) designed for selecting optimal network paths for Multi-Path TCP (MPTCP) clients. It uses various genetic operations like crossover, mutation, and selection to evolve a population of chromosomes, each representing a potential solution for path selection based on network characteristics like bandwidth and delay.

## Features
- Custom genetic algorithm implementation.
- Tournament selection strategy.
- Two-way crossover and mutation operations.
- Fitness calculation based on bandwidth and delay metrics using the formula: `0.6 * a + 0.4 * b`, where `a` is the total sum of the bandwidth of the three genes in the chromosome, and `b` is the difference between the delays of the genes.


## Implementation

1- Install Apache2:
https://httpd.apache.org/

2- Download the Dash client. The dash client is the modified version of the Industry Dash video client presented at "https://github.com/Dash-Industry-Forum/dash.js" which was modified to print the specific information regarding the streamed video in a Chrome log file. The modification can be seen at "/dash.js/samples/dash-if-reference-player/app/main.js". Move the directory of the client  to the Apache server folder (/var/www/html) and install the video client using the tutorial of the industry dash forum "https://github.com/Dash-Industry-Forum/dash.js"


3- Download the video files with the preferred bitrate:
https://ftp.itec.aau.at/datasets/DASHDataset2014/
I have used the BigBuckBunny video presentation. However, a different video and presentation can be preferred. 

4- I have developed a software called "Exelizer" to read the log file from the Google Chromium browser which the Dash Client runs on, and calculate the QoE parameters. Use it to print out a text file including the average received video bitrate, the number of quality switches, and the duration of the underrun. 

6- Install Java Development Kit (JDK), version 8 or later

4- Install Eclipse Java EE: 
https://www.eclipse.org/downloads/

5- Install SDN Controller (I have used Floodlight but for easier implementation, you might prefer ONOS)
https://floodlight.atlassian.net/wiki/spaces/floodlightcontroller/overview?homepageId=1343545
The controller can be run on the Eclipse to perform path assignment. 




## Usage

1. Ensure you have Java installed on your machine.

2. Store the information regarding the network topology, bandwidth, and delay of the links in the graph.txt file. This file will be used in the Pathfinder application to study and list all possible paths from a source to a destination with the related bandwidth and delay values. 

3. The paths with their bandwidth and delay values will be used as the initial population in the "genet.txt" file. Place the `genes.txt` file in the same directory as the Java source files. This file will be used as the input in the genetic algorithm application. The application will read the "gene.txt" file as the initial population and will try to find an optimal path.

4. Run the Genetic Algorithm. Follow the prompts to specify the program parameters such as population size, tournament size, mutation rate, and number of generations based on your preference.

6. The program will display the progress of each generation. Finally, it will output the fittest chromosome in the last population and its corresponding gene.


## Customization
You can modify various parameters of the genetic algorithm, such as the number of generations, population size, mutation rate, etc., in the respective Java classes.

## Authors
Shadi Bikas




Feel free to modify and adapt the code to suit your needs.


