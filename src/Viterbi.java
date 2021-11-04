/*
*
* @author: Cheng Huang
* the implement of Viterbi
* */
public class Viterbi {
    /**
     * 此函数就是为了在已知obs的情况下，求解最大可能的隐藏序列
     * @param obs 观察到的结果(walk, shop, clean)
     * @param states 隐含状态(Raniy, Sunny)
     * @param start_p 初始状态值
     * @param trans_p 隐含状态之间的转换矩阵
     * @param emit_p 隐含状态到显示状态之间的转换关系
     * @return 最可能的序列
     */
    public static int[] compute(int[] obs, int [] states, double[] start_p, double[][] trans_p, double[][] emit_p){
        double[][] V = new double[obs.length][states.length];
        int [][] path = new int[states.length][obs.length];

        //day 0的状态（我们假设天数从0开始）
        for(int state : states){
            V[0][state] = start_p[state] * emit_p[state][obs[0]];
            path[state][0] = state;
        }
        //day 1 开始
        for(int t = 1; t < obs.length; t ++){
            int[][] newpath = new int[states.length][obs.length];
            for(int y : states){
                double prob = -1;
                int state;
                for(int y0 : states){
                    //day t 为 （day t-1的状态) * (day t-1 -> day t的转换概率) * (emission state)
                    double nprob = V[t-1][y0] * trans_p[y0][y] * emit_p[y][obs[t]];
                    if(nprob > prob){
                        prob = nprob;
                        state = y0;
                        V[t][y] = prob;
                        System.arraycopy(path[state], 0, newpath[y], 0, t);
                        newpath[y][t] = y;
                    }
                }
            }
            path = newpath;
        }

        double prob = -1;
        int state = 0;
        for(int y : states){
            if(V[obs.length-1][y] > prob){
                prob = V[obs.length-1][y];
                state = y;
            }
        }
        return path[state];
    }
}
