public class WeatherExample {
    // state 隐藏序列
    enum Weather{
        Rainy,
        Sunny,
    }
    // observations 可观测序列
    enum Activity{
        walk,
        shop,
        clean,
    }
    //ordinal() return the index in enum class;
    static int[] states = new int[]{Weather.Rainy.ordinal(), Weather.Sunny.ordinal()};
    static int[] observations = new int[]{Activity.walk.ordinal(), Activity.shop.ordinal(), Activity.clean.ordinal()};
    // initial probability for Rainy = 0.6 (隐藏state)
    // initial probability for Sunny = 0.4 (隐藏state)
    static double[] start_probability = new double[]{0.6, 0.4};
    // Rainy -> Rainy = 0.7
    // Rainy -> Sunny = 0.3
    // Sunny -> Rainy = 0.4
    // Sunny -> Sunny = 0.6
    // 隐藏状态之间的相互转换
    static double[][] transition_probability = new double[][]{
            {0.7, 0.3},
            {0.4, 0.6}
    };
    // Rainy->(walk, shop, clean) = {0.1, 0.4, 0.5}
    // Sunny->(walk, shop, clean) = {0.6, 0.3, 0,1}
    // 隐藏状态到显示状态之间的转换
    static double[][] emission_probability = new double[][]{
            {0.1, 0.4, 0.5},
            {0.6, 0.3, 0.1}
    };

    public static void main(String[] args) {
        int[] results = Viterbi.compute(observations, states, start_probability, transition_probability, emission_probability);
        for(int result : results){
            System.out.print(Weather.values()[result] + " ");
        }
    }

}
