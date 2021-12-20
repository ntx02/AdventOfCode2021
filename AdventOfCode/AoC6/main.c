#include <stdio.h>
#include <time.h>

void setVals(int sample[], int lengthSample, long long int distribution[])
{
    for (int i = 0; i < lengthSample; i++)
    {
        long long int currNum = (long long int)sample[i];
        distribution[currNum] = distribution[currNum] + 1L;
    }
}

void simulate(long long int distribution[], int length, int days)
{
    for (int i = 0; i < days; i++)
    {
        long long int temp = distribution[0];

        for (int j = 0; j < length - 1; j++)
        {
            distribution[j] = distribution[j + 1];
        }
        distribution[6] = distribution[6] + temp;
        distribution[length - 1] = temp;
    }
}

long long int sumFish(long long int distribution[], int length)
{long long int sumFish = 0;

    for (int i = 0; i < length; i++)
    {
        long long int curr = distribution[i];
        sumFish += curr;
    }

    return sumFish;
}

int main(void)
{
    double t = clock();

    int fish[] = {4,1,1,1,5,1,3,1,5,3,4,3,3,1,3,3,1,5,3,2,4,4,3,4,1,4,2,2,1,3,5,1,1,3,2,5,1,1,4,2,5,4,3,2,5,3,3,4,5,4,3,5,4,2,5,5,2,2,2,3,5,5,4,2,1,1,5,1,4,3,2,2,1,2,1,5,3,3,3,5,1,5,4,2,2,2,1,4,2,5,2,3,3,2,3,4,4,1,4,4,3,1,1,1,1,1,4,4,5,4,2,5,1,5,4,4,5,2,3,5,4,1,4,5,2,1,1,2,5,4,5,5,1,1,1,1,1,4,5,3,1,3,4,3,3,1,5,4,2,1,4,4,4,1,1,3,1,3,5,3,1,4,5,3,5,1,1,2,2,4,4,1,4,1,3,1,1,3,1,3,3,5,4,2,1,1,2,1,2,3,3,5,4,1,1,2,1,2,5,3,1,5,4,3,1,5,2,3,4,4,3,1,1,1,2,1,1,2,1,5,4,2,2,1,4,3,1,1,1,1,3,1,5,2,4,1,3,2,3,4,3,4,2,1,2,1,2,4,2,1,5,2,2,5,5,1,1,2,3,1,1,1,3,5,1,3,5,1,3,3,2,4,5,5,3,1,4,1,5,2,4,5,5,5,2,4,2,2,5,2,4,1,3,2,1,1,4,4,1,5};
    int fishLength = 300;

    long long int numEach[9] = {0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
    int distLength = 9;

    int days = 256;

    setVals(fish, fishLength, numEach);
    simulate(numEach, distLength, days);
    t = (clock() - t) / CLOCKS_PER_SEC;

    printf("Number of fish after %d days: %lld\n", days, sumFish(numEach, distLength));
    printf("Time taken to simulate %d days: %f seconds", days, t);
}
