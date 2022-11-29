// Program that creates child and parent processes using piping 

#include <errno.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {

  int fd[2]; // Used to store two ends of first pipe
  int nums[] = {3, 8, 12, 21, 1, 7, 23, 18, 15, 10};
  int parentSum, childSum, result = 0;
  pid_t p;

  if (pipe(fd) == -1) {
    fprintf(stderr, "Pipe Failed");
    return 1;
  }

  p = fork();

  if (p < 0) {
    fprintf(stderr, "fork Failed");
    return 1;
  }

  // Child process
  else if (p == 0) {

    close(fd[0]); // Close reading end of first pipe

    childSum = 0;
    for (int i = 0; i < 5; i++) {
      childSum += nums[i];
    }
    write(fd[1], &childSum, sizeof(childSum));
    close(fd[1]);

  }

  // parent process
  else {
    close(fd[1]); // Close writing end of first pipe

    int paretnSum = 0;
    for (int i = 5; i < 10; i++) {
      paretnSum += nums[i];
    }

    // Wait for child to finish
    wait(NULL);

    int val;
    read(fd[0], &val, sizeof(val));
    paretnSum += val;

    printf("%d\n", paretnSum);
    close(fd[0]);
  }
}
