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

2- Install Dash client and move to the Apache server folder (/var/www/html):
https://github.com/Dash-Industry-Forum/dash.js

3- Download the video files with the preferred bitrate:
https://ftp.itec.aau.at/datasets/DASHDataset2014/

4- I have developed a software called "Exelizer" to read the log file from the Google Chromium which the Dash Client runs on, and calculate the QoE parameters. Use it to print out a text file including the average received video bitrate, number of quality switched and duration of underrun. 

4- Install Eclipse Java EE:
https://www.eclipse.org/downloads/

5- Install SDN Controller (I have used Floodlight but for easier implementation, you might prefer ONOS)
https://floodlight.atlassian.net/wiki/spaces/floodlightcontroller/overview?homepageId=1343545

6- Java Development Kit (JDK), version 8 or later


## Usage

1. Ensure you have Java installed on your machine.

2. Place the `genes.txt` file in the same directory as the Java source files.

3. Compile the Java source files using the following command:

javac Main.java

4. Run the program using the following command:

java Main


5. Follow the prompts to specify the program parameters such as population size, tournament size, mutation rate, and number of generations.

6. The program will display the progress of each generation, including the fitness values of the chromosomes and their gene values. Finally, it will output the fittest chromosome in the last population and its corresponding gene.


## Customization
You can modify various parameters of the genetic algorithm, such as the number of generations, population size, mutation rate, etc., in the respective Java classes.

## Authors
Shadi Bikas




Feel free to modify and adapt the code to suit your needs.


