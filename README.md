# 1002_TNSM
This repository is created for all the developments made during the TUBITAK 1002 project.

# Genetic Algorithm for Optimal Path Selection

This Java program demonstrates the use of a genetic algorithm to solve a path selection in a network with an MPTCP transport protocol optimization problem. The goal is to find the fittest chromosome in a population by evolving and evaluating the individuals over multiple generations.

## Problem Description

The problem involves optimizing a population of chromosomes, where each chromosome consists of three genes. The values of the genes are read from a text file called `gene_values.txt`, which contains 250 lines. Each line represents a gene and consists of three numbers: gene value, bandwidth, and delay.

The fitness of each chromosome is calculated using the formula: `0.6 * a + 0.4 * b`, where `a` is the total sum of the bandwidth of the three genes in the chromosome, and `b` is the difference between the delays of the genes.

## Implementation Details

The program uses a genetic algorithm approach with the following components:

- **Chromosome**: Represents an individual chromosome with three genes. It includes methods for calculating fitness, crossover, and mutation.

- **Population**: Represents a population of chromosomes. It includes methods for evaluating the population, performing tournament selection, and evolving to the next generation.

- **Main**: The main class that initializes and evolves the population over a specified number of generations. It also reads the gene values from the text file and displays the fittest chromosome and its corresponding gene in the last population.

## Implementation

1- Install Apache2:
https://httpd.apache.org/

2- Install Dash client and move to the Apache server folder (/var/www/html):
https://github.com/Dash-Industry-Forum/dash.js

3- Download the video files with the preferred bitrate:
https://ftp.itec.aau.at/datasets/DASHDataset2014/

4- Install Eclipse Java EE:
https://www.eclipse.org/downloads/

5- Install SDN Controller (I have used Floodlight but for easier implementation, you might prefer ONOS)
https://floodlight.atlassian.net/wiki/spaces/floodlightcontroller/overview?homepageId=1343545


## Usage

1. Ensure you have Java installed on your machine.

2. Place the `gene_values.txt` file in the same directory as the Java source files.

3. Compile the Java source files using the following command:

javac Main.java

4. Run the program using the following command:

java Main


5. Follow the prompts to specify the program parameters such as population size, tournament size, mutation rate, and number of generations.

6. The program will display the progress of each generation, including the fitness values of the chromosomes and their gene values. Finally, it will output the fittest chromosome in the last population and its corresponding gene.

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to modify and adapt the code to suit your needs.


