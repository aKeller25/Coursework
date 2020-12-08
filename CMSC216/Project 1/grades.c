#include <stdio.h>
#include <math.h>

#define MAX_ASSIGNMENTS 50

/* Name: Alexander Keller
 * UID: 113600425
 * DID: akeller5*/

static double calc_std_dev(double mean, int num_of_assign,
  int assign_days_late[], int penalty_per_day, int assign_score[]);
static double calc_mean(int num_of_assign, int assign_days_late[],
  int penalty_per_day, int assign_score[]);
static int find_assign(double assign_indiv_scores[], int num_of_assign,
  double indiv_score);

int main() {
  int assign_num[MAX_ASSIGNMENTS], assign_score[MAX_ASSIGNMENTS],
  assign_weight[MAX_ASSIGNMENTS], assign_days_late[MAX_ASSIGNMENTS];

  int assign_num_dropped, penalty_per_day, assign_current, num_of_assign,
  num_of_passes, lowest_score_idx, total_weight, below_zero,
  dropped_weight_counter = 0, counter, tmp, tmp2, tmp3, tmp4;

  double score = 0, mean, std_dev, assign_indiv_scores[MAX_ASSIGNMENTS],
  assign_weight_dropped[MAX_ASSIGNMENTS], lowest_score;

  char stat_info;

  /* scanning in values*/
  scanf("%d %d %c", &penalty_per_day, &assign_num_dropped, &stat_info);
  scanf("%d", &num_of_assign);

  for(assign_current = 0; assign_current < num_of_assign; assign_current++) {

    scanf("%d, %d, %d, %d", &assign_num[assign_current],
    &assign_score[assign_current], &assign_weight[assign_current],
    &assign_days_late[assign_current]);

    if(assign_num[assign_current] < 1 || assign_num[assign_current] >
      MAX_ASSIGNMENTS) {
        printf("ERROR: Invalid values provided\n");

        return 0;
    }
  }

  for(assign_current = 0; assign_current < num_of_assign; assign_current++) {
    total_weight += assign_weight[assign_current];
  }

  if(total_weight < 100) {
    printf("ERROR: Invalid values provided\n");

    return 0;
  }

  /* Sorting using bubble sort*/
  for (num_of_passes = 0; num_of_passes < num_of_assign - 1; num_of_passes++) {
    for(assign_current = 0; assign_current < num_of_assign - 1;
      assign_current++) {

      if(assign_num[assign_current] > assign_num[assign_current + 1]) {
        tmp = assign_num[assign_current + 1];
        assign_num[assign_current + 1] = assign_num[assign_current];
        assign_num[assign_current] = tmp;

        tmp2 = assign_score[assign_current + 1];
        assign_score[assign_current + 1] = assign_score[assign_current];
        assign_score[assign_current] = tmp2;

        tmp3 = assign_weight[assign_current + 1];
        assign_weight[assign_current + 1] = assign_weight[assign_current];
        assign_weight[assign_current] = tmp3;

        tmp4 = assign_days_late[assign_current + 1];
        assign_days_late[assign_current + 1] =
        assign_days_late[assign_current];
        assign_days_late[assign_current] = tmp4;

      }
    }
  }

  /* Copying the values of the weight into the dropped weights for
   calculating with dropped assignemnts*/
  for(assign_current = 0; assign_current < num_of_assign; assign_current++) {
    assign_weight_dropped[assign_current] = assign_weight[assign_current];
  }

  if(assign_num_dropped != 0) {
  /* Calculaing individual scores so that they can be dropped later*/
  for(assign_current = 0; assign_current < num_of_assign; assign_current++) {
    assign_indiv_scores[assign_current] =
    ((double) assign_score[assign_current] *
    (double) assign_weight[assign_current]) / 100.00;
  }

  /* Dropping scores*/
  for(num_of_passes = 0; num_of_passes < assign_num_dropped;
    num_of_passes++) {
      lowest_score = 100.00;

      for(assign_current = 0; assign_current < num_of_assign;
        assign_current++) {

          if(assign_indiv_scores[assign_current] <
            lowest_score && assign_weight_dropped[assign_current] != 0) {

            lowest_score = assign_indiv_scores[assign_current];
          }
        }
        lowest_score_idx = find_assign(assign_indiv_scores, num_of_assign,
          lowest_score);
        assign_weight_dropped[lowest_score_idx] = 0;
        dropped_weight_counter += assign_weight[lowest_score_idx];
      }

  /* Adjusting the weights of the assignments that were not dropped*/
  for(counter = 0; counter < num_of_assign; counter++) {
    if(assign_weight_dropped[counter] != 0) {
      assign_weight_dropped[counter] +=
      ((double) dropped_weight_counter /
      ((double) num_of_assign - (double) num_of_passes));
    }
  }
}
  /* Calculating the numerical score*/
  for(assign_current = 0; assign_current < num_of_assign; assign_current++) {
    below_zero = ((double) assign_score[assign_current] -
    ((double) assign_days_late[assign_current] * (double) penalty_per_day));

    if(below_zero < 0) {
      below_zero = 0;
    }

    score += below_zero *(double) assign_weight_dropped[assign_current];
   }

  score /= 100.00;

  /* Printing the output*/
  printf("Numeric Score: %5.4f\n", score);
  printf("Points Penalty Per Day Late: %d\n", penalty_per_day);
  printf("Number of Assignments Dropped: %d\n", assign_num_dropped);
  printf("Values Provided:\n");
  printf("Assignment, Score, Weight, Days Late\n");

  /* Printing the assignment numbers, scores, weights, and days late*/
  for(assign_current = 0; assign_current < num_of_assign; assign_current++) {
    printf("%d, %d, %d, %d\n", assign_num[assign_current],
    assign_score[assign_current], assign_weight[assign_current],
    assign_days_late[assign_current]);
  }

  /* Calculating and printing the statistical information if asked for*/
  if(stat_info == 'y' || stat_info == 'Y') {
    mean = calc_mean(num_of_assign, assign_days_late, penalty_per_day,
      assign_score);
    std_dev = calc_std_dev(mean, num_of_assign, assign_days_late,
      penalty_per_day, assign_score);

    printf("Mean: %5.4f, Standard Deviation: %5.4f\n", mean, std_dev);
  }
  return 0;
}

/* A helper function that calculates the mean of the scores. It takes in
 the number of assignments, an array storing how many days late an
 assignment is, the penalty per day late, and an array that stores the
 assignemnt scores*/
static double calc_mean(int num_of_assign, int assign_days_late[],
  int penalty_per_day, int assign_score[]) {
  double mean;
  int below_zero, counter;

  for(counter = 0; counter < num_of_assign; counter++) {

    below_zero = (double) assign_score[counter] -
    ((double) assign_days_late[counter] * (double) penalty_per_day);

    if(below_zero < 0) {
      below_zero = 0;
    }
    mean += below_zero;
  }
  mean = mean / (double) num_of_assign;

  return mean;
}

/* A helper function that calculates the standard deviation of the scores.
 It takes in the mean, the number of assignments, an array storing how many
 days late each assignment is, the penalty per day late, and array that
 stores the assignemnt scores*/
static double calc_std_dev(double mean, int num_of_assign,
  int assign_days_late[], int penalty_per_day, int assign_score[]) {
  double sqrt_mean[MAX_ASSIGNMENTS], mean_sqrt_mean;
  int counter;

  for(counter = 0; counter < num_of_assign; counter++) {
    sqrt_mean[counter] = pow((((double)assign_score[counter] -
      ((double) assign_days_late[counter] *
      (double) penalty_per_day)) - mean), 2);
  }

  for(counter = 0; counter < num_of_assign; counter++) {
    mean_sqrt_mean +=  sqrt_mean[counter];
  }
  mean_sqrt_mean = mean_sqrt_mean / (double) num_of_assign;

  return sqrt(mean_sqrt_mean);
}

/* A helper method that finds a specific score in an array. It takes in an
 array of individual scores, number of assignments, and the individual
 score that is being searched for*/
static int find_assign(double assign_indiv_scores[], int num_of_assign,
  double indiv_score) {
  int counter;

  for(counter = 0; counter < num_of_assign; counter++) {
    if(assign_indiv_scores[counter] == indiv_score) {
      return counter;
    }
  }
  return -1;
}
