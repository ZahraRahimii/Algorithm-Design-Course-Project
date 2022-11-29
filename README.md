# Algorithm Design Course Project
Here are the project of the Algorithm Design course including:
* Arragement of weighted colored cubes;
* Eight ministers (or queens) problem with obstacle
* Eight ministers (or queens) problem with scored obsatcle

##  Arragement of weighted colored cubes
Consider N number of cubes of the same size, each of which has a different weight. Each of the faces of the cubes has a color. We want to place the cubes on top of each other to design the tallest tower while observing the following:
a) When placing one cube on top of another, the lower face of the upper cube must be the same color as the upper face of the lower cube.
b) When placing one cube on top of another, the lower cube should be heavier than the upper cube.

## Eight ministers (or queens) problem with obstacle
In the issue of eight ministers, some chess houses have walls and prevent threats. It is not possible to place the minister in houses that are covered with walls. We want to find all the possible positions that eight ministers can be placed in such an environment. The following example is an example of a solution:

<p align="center">
<img src="https://user-images.githubusercontent.com/93929227/204458807-e77c6f1a-7366-444c-8936-c67b403ff53a.png" width="30%" height="30%">
<p/>

## Eight ministers (or queens) problem with scored obsatcle
In the previous problem, we have distributed the numbers 1 to 64 as points between the houses of the page. If for each possible solution, we consider the total points of the location of the ministers as the points of that solution. We want to eesign and implement an optimal algorithm that, by taking a number like z, finds and displays only solutions whose score is greater than z.
