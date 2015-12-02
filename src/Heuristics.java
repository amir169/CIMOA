/**
 * Created by Amir Shams on 11/29/2015.
 */
public class Heuristics
{
    int[][] worldMatrix;
    int[][] LRTAStarmatrix;

    public void setWorldMatrix(int[][] worldMatrix) {
        this.worldMatrix = worldMatrix;
    }


    //must call in constructor
    void initialLRTAStarMatrx(){
        for (int i = 0; i < worldMatrix.length; i++) {
            for (int j = 0; j < worldMatrix[0].length; j++) {
                if(worldMatrix[i][j]==0)LRTAStarmatrix[i][j]=-100;
                else LRTAStarmatrix[i][j]=100;
            }
        }
    }
}
