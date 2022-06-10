package GH;

public class Club_codinggame_problem {
    String problem_q;
    String[]problem_codes;
    public Club_codinggame_problem(String problem_q, String[]a){
        this.problem_q = problem_q;
        problem_codes =  new String[7];
        for(int i = 0; i<7; i++){
            problem_codes[i] = a[i];
        }

    }
    public String getProblem_q(){
        return problem_q;
    }
    public String[] getProblem_codes(){
        return problem_codes;
    }
}
