import java.util.*;

public class A2Q1MyRandomPlayer implements A2Q1AI {


   int myNum;
   public A2Q1GameI.Move move(A2Q1GameI game) {
      A2Q1GameI.Move move;
      myNum = game.currentPlayer();
      State s1 = new State(game.board(),game.currentPlayer());
      int stopLevel = 21;
      int m = minimax(s1,stopLevel);
      //Math.pow();
      move = A2Q1GameI.Move.values()[m];
      return move;
   }

   public LinkedList<State> getAllchild(State s)
   {
      char[][] b = s.board;
      int x = s.x;
      int y = s.y;


      LinkedList<State> r = new LinkedList<State>();

      if(x!=0 &&b[x-1][y] == ' ') {
         State i = s.cloneSon(myNum,1);
         r.add(i);

      }
      if(x!=b.length-1 &&b[x+1][y] == ' ') {
         State i = s.cloneSon(myNum,2);
         r.add(i);
      }
      if(y!=b[x].length-1 && b[x][y+1] == ' ') {
         State i = s.cloneSon(myNum,3);
         r.add(i);
      }
      if(y!=0 && b[x][y-1] == ' ') {
         State i = s.cloneSon(myNum,4);
         r.add(i);
      }
      return r;
   }

   public int minimax(State s,int stop)
   {
      int alpha = Integer.MIN_VALUE;
      int beta =Integer.MAX_VALUE;
      int best = -1;
      int pos = -1;
      int move = 0;
      LinkedList<State> all = getAllchild(s);
      if(!all.isEmpty())
      {
         for (int i = 0; i < all.size(); i++)
         {
            int score = minV(all.get(i),alpha,beta,stop);
            if (score > best)
            {
               best = score;
               pos = i;
            }

            if(score >= beta){ return all.get(pos).direct; }
            alpha = Math.max(alpha,score);

         }
         move = all.get(pos).direct;
      }
      return move;
   }

   public int maxV(State s,int alpha,int beta,int stop)
   {
      LinkedList<State> r = getAllchild(s);
      //System.out.println(r);
      if(r.isEmpty() || s.level > stop)
      {

         /* for(int i = 0;i<s.board.length;i++)
            {
               for (int j = 0; j < s.board[i].length; j++)
               {
                  if (s.board[i][j] == s.mypath) {
                     s.block++;
                  }
                  if (s.board[i][j] == s.oppath) {
                     s.opblock++;
                  }

               }
            }
               if(s.block>s.opblock){
                  s.scores = 5;
               }else if (s.block==s.opblock){
                  s.scores = 3;
               }else{
                  s.scores = 2;
               }*/

         return s.block;
      }
      int v = Integer.MIN_VALUE;

      for(int i=0;i<r.size();i++)
      {
         //System.out.println(r.get(i).scores);
         v = Math.max(v,minV(r.get(i),alpha,beta,stop));
         if(v >= beta){
            return v;
         }
         alpha = Math.max(alpha,v);
      }

      return v;
   }
   public int minV(State s,int alpha,int beta,int stop)
   {
      LinkedList<State> r = getAllchild(s);
      int nextNum = (s.currP)%(s.numP)+1;
      int v;
      if(nextNum == myNum)
      {
         if (r.isEmpty()||s.level>stop)
         {
            return s.block;
           /* State i = s.cloneSon(myNum,0);
            r.add(i);
            v = maxV(r.get(0),alpha,beta,stop);*/
         }
         else
         {
            v = Integer.MAX_VALUE;
            for (int i = 0; i < r.size(); i++)
            {
               v = Math.min(v, maxV(r.get(i),alpha,beta,stop));
               if(v <= alpha)
               {
                  return v;
               }
               beta = Math.min(beta,v);
            }
         }
      }
      else
      {
         if (r.isEmpty()||s.level>stop) {
            State i = s.cloneSon(myNum,0);
            r.add(i);
            v = minV(r.get(0),alpha,beta,stop);
         } else {
            v = Integer.MAX_VALUE;
            for (int i = 0; i < r.size(); i++)
            {
               v = Math.min(v, minV(r.get(i),alpha,beta,stop));
               if(v <= alpha)
               {
                  return v;
               }
               beta = Math.min(beta,v);
            }
         }
      }
      return v;
   }
   public String toString() {
      return "Harryharries";
   }

}