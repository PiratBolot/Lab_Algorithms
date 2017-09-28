#include <fstream>
#include <cmath>
#include <iostream>
 
int way();
 
int** matrix;
int** resultMatrix;
int w;
int h;
 
int main(){
    freopen("turtle.in", "r", stdin);
    freopen("turtle.out", "w+", stdout);
    std::cin >> h;    
    std::cin >> w;
    matrix = new int* [h];
    resultMatrix = new int* [h];
    for (int i = 0; i < h; i++) {
        resultMatrix[i] = new int[w];
        matrix[i] = new int[w];     
        for (int j = 0; j < w; j++)
            std::cin >> matrix[i][j];
    }
    int result = way();
    std::cout << result;
    fclose(stdin);
    fclose(stdout);
    return 0;
}
 
int way()
{
    for (int i = h - 1; i >= 0; i--) {
        for (int j = 0; j < w; j++) {
            if (i == h - 1 && j == 0) {
                resultMatrix[i][j] = matrix[h - 1][0];
            }
            else if (i < h - 1 && j == 0) {
                resultMatrix[i][j] = resultMatrix[i + 1][j] + matrix[i][j];
            }
            else if (i == h - 1 && j > 0) {
                resultMatrix[i][j] = resultMatrix[i][j - 1] + matrix[i][j];
            }
            else {
                resultMatrix[i][j] = static_cast<int>(fmax(resultMatrix[i + 1][j], resultMatrix[i][j - 1])) + matrix[i][j];
            }
        }
    }
    return resultMatrix[0][w - 1];
}
