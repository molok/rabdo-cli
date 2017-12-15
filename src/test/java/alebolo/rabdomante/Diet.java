package alebolo.rabdomante;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression;
import org.chocosolver.solver.objective.ParetoOptimizer;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Diet {

    public static void main(String[] args) {
        diet();
    }

    private static void diet() {

        Model model = new Model("acqua");
        int nWaters = 3;
        int[][] wMinerals =
         /* D, LV, VR*/
        { { 0, 10, 20}, /*calcio*/
          { 0, 10, 20}  /*magnesio*/ };
        int CALCIO = 0;
        int MAGNESIO = 1;

        final int BATCH_SIZE_L = 2;
        int[] targetProfile = { 15,
                                15 };

        IntVar[] varWaters = new IntVar[nWaters];

        varWaters[0] = model.intVar("distillata", 0, BATCH_SIZE_L);
        varWaters[1] = model.intVar("levissima", 0, BATCH_SIZE_L);
        varWaters[2] = model.intVar("vera", 0, BATCH_SIZE_L);
        model.sum(varWaters, "=", BATCH_SIZE_L).post();

        /* massimo due acque, esattamente 10L */
//        varWaters[0].add(varWaters[1]).add(varWaters[2]).eq(BATCH_SIZE_L).post();


        IntVar sumCalcio = model.intVar("costCalcio", 0);
        IntVar sumMagnesio = model.intVar("costMagn", 0);

        for (int w = 0; w < nWaters; w++) {
            sumCalcio = sumCalcio.add(model.intVar(wMinerals[CALCIO][w]).mul(varWaters[w])).intVar();
            sumMagnesio = sumMagnesio.add(model.intVar(wMinerals[MAGNESIO][w]).mul(varWaters[w])).intVar();

            System.out.println("calcio:"+sumCalcio.toString());
            System.out.println("magn:"+sumMagnesio.toString());
        }
        ArExpression distCalcio = sumCalcio.dist(targetProfile[CALCIO] * BATCH_SIZE_L);
        ArExpression distMagn = sumMagnesio.dist(targetProfile[MAGNESIO] * BATCH_SIZE_L);
        System.out.println("calcio:"+distCalcio.toString());
        System.out.println("magn:"+distMagn.toString());

        IntVar cost = model.intVar("KOSTO", 0).add(distCalcio.add(distMagn)).intVar();

        System.out.println("KOSTO " + cost.getValue());

        model.setObjective(Model.MINIMIZE, cost);
        Solver solver = model.getSolver();
        while(solver.solve()) {
            System.out.print("cost:"+cost.getValue());
            System.out.println(" varW:"+Arrays.toString(varWaters));
            int zcalcio = 0;
            int zmagn = 0;
            for (int w = 0; w < nWaters; w++) {
                int thiscalc = wMinerals[CALCIO][w] * varWaters[w].getValue();
                int thismagn = wMinerals[MAGNESIO][w] * varWaters[w].getValue();
                zcalcio += thiscalc;
                zmagn += thismagn;
//                System.out.println(String.format("d:%d, l:%d, v:%d => thiscalcio:%d, thismagn:%d"
//                        , varWaters[0].getValue()
//                        , varWaters[1].getValue()
//                        , varWaters[2].getValue()
//                        , thiscalc, thismagn ));
            }
            int delta = Math.abs( (targetProfile[CALCIO] * BATCH_SIZE_L) - zcalcio)
                    +   Math.abs( (targetProfile[MAGNESIO] * BATCH_SIZE_L) - zmagn);
            System.out.println(String.format("d:%d, l:%d, v:%d => zcalcio:%d, zmagn:%d, delta:%d\n\n"
                    , varWaters[0].getValue()
                    , varWaters[1].getValue()
                    , varWaters[2].getValue()
                    , zcalcio
                    , zmagn
                    , delta
            ));


        }

//        solver.findAllSolutions().stream().forEach(s -> System.out.println(s.toString()));

        // create an object that will store the best solutions and remove dominated ones
//        ParetoOptimizer po = new ParetoOptimizer(Model.MINIMIZE, new IntVar[] {cost});
//        Solver solver = model.getSolver();
//        solver.plugMonitor(po);
//
//        while(solver.solve());
//
//        List<Solution> paretoFront = po.getParetoFront();
//        System.out.println("The pareto front has "+paretoFront.size()+" solutions : ");
//        for(Solution s : paretoFront){
//            System.out.println("calcio:"+sumCalcio.toString());
//            System.out.println("magn:"+sumMagnesio.toString());
//            System.out.println("cost = " + s.getIntVal(cost) + ", solution = "+s.toString());
//        }

//        Solver solver = model.getSolver();
//
//        while(solver.solve()) {
//            solver.
//        }
//
//        solver.findAllSolutions().stream().forEach(s -> System.out.println(s.toString())+s.c);
//        solver.printStatistics();
//        System.out.println(solution.toString());
    }

    private static void queens() {
        int n = 8;
        Model model = new Model(n + "-queens problem");
        IntVar[] vars = new IntVar[n];
        for(int q = 0; q < n; q++){
            vars[q] = model.intVar("Q_"+q, 1, n);
        }
        for(int i  = 0; i < n-1; i++){
            for(int j = i + 1; j < n; j++){
                model.arithm(vars[i], "!=",vars[j]).post();
                model.arithm(vars[i], "!=", vars[j], "-", j - i).post();
                model.arithm(vars[i], "!=", vars[j], "+", j - i).post();
            }
        }
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
        }
    }

    private static void mah() {

    }

    private static void demo() {
        int N = 100;
        // 1. Modelling part
        Model model = new Model("all-interval series of size " + N);
        // 1.a declare the variables
        IntVar[] S = model.intVarArray("s", N, 0, N - 1, false);
        IntVar[] V = model.intVarArray("V", N - 1, 1, N - 1, false);
        // 1.b post the constraints
        for (int i = 0; i < N - 1; i++) {
            model.distance(S[i + 1], S[i], "=", V[i]).post();
        }
        model.allDifferent(S).post();
        model.allDifferent(V).post();
        S[1].gt(S[0]).post();
        V[1].gt(V[N - 2]).post();

        // 2. Solving part
        Solver solver = model.getSolver();
        // 2.a define a search strategy
        solver.setSearch(Search.minDomLBSearch(S));
        if (solver.solve()) {
            System.out.printf("All interval series of size %d%n", N);
            for (int i = 0; i < N - 1; i++) {
                System.out.printf("%d <%d> ",
                        S[i].getValue(),
                        V[i].getValue());
            }
            System.out.printf("%d", S[N - 1].getValue());
        }
    }
}