import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class App {
    private static int numCoco;
    private static List<Macaco> macacos = new ArrayList<>();
    private static int rodada;
    /*
     * @param indiceGanhador onde sera armazenado o indice do macaco que tem mais cocos
     * @param quantCocoGanhador onde sera armazenado a qntdade de cocos do macaco chefe
     * @param i indice
     * @param numPar tam total de cocos do macaco par
     * @param numImpar tam total de cocos do macaco impar
     */
    public static void main(String[] args) throws Exception {
        int indiceGanhador = 0;
        int quantCocoGanhador = 0;
        int i = 0;
        // int numPar;
        // int numImpar;

        readFile("caso1000.txt");

        long startTime = System.currentTimeMillis();

        for(int r = 0; r < rodada; r++){
            for (Macaco m : macacos){
                int valorPar = m.getCocoPar() + macacos.get(m.getMandaPar()).getCocoPar();
                int valorImpar = m.getCocoImpar() + macacos.get(m.getMandaImpar()).getCocoImpar();
                macacos.get(m.getMandaPar()).setCocoPar(valorPar);
                macacos.get(m.getMandaImpar()).setCocoImpar(valorImpar);
                m.setCocoImpar(0);
                m.setCocoPar(0);
            }
        }
        
        //funcao para identificar o macaco com maio numero de cocos
        for(Macaco m : macacos){
            int cocoT = m.getTotal(); //pega o num de cocos do macaco atual

            //verifica se o cocot eh maior que o cocot anterior
            if(cocoT > quantCocoGanhador){
               indiceGanhador = i; //armazena o indice do macaco chefe
               quantCocoGanhador = cocoT; //armazena a qntdade de cocos do chefe
            }
            ++i;
        }
        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime);
        System.out.println("Execution time: " + executionTime + " miliseconds");

        System.out.println("macaco vencedor foi o numero: " + indiceGanhador + ", com: " + quantCocoGanhador + " cocos");
    }

    public static boolean readFile(String nomeArq) {
        Path path1 = Paths.get(nomeArq);
        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if(!line.contains("Macaco")){
                    int rodadas = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    rodada = rodadas;
                }else{

                    String[] dados1 = line.split(" : "); // dividir - "Macaco n par -> n impar -> n"  "numero de cocos" "cocos com quantidade de pedras dentro"
                                                                            //    0                                 1                                2      
                    String[] dados2 = dados1[2].split(" ");//vetor dos cocos com pedras

                    int numCocos = Integer.parseInt(dados1[1]); //transforma o dados1[1] em int
                    numCoco = numCocos;

                    LinkedList<Integer> cocos = (LinkedList<Integer>) transfArray(dados2);      //transforma a lista de cocos numa lista de cocos inteiros

                    String regex = "[^\\d]+";
                    String[] trocasMacacos = dados1[0].split(regex);//macacos que passara os cocos pares e impares
                    
                    int numPar = 0;
                    int numImpar = 0;
                    for(int c: cocos){
                        if(c%2==0){
                            numPar++;
                        }
                        else{
                            numImpar++;
                        }
                    }
                                                           //par                               //impar
                    Macaco macaquinhos = new Macaco(Integer.parseInt(trocasMacacos[2]), Integer.parseInt(trocasMacacos[3]), numPar, numImpar);
                    macacos.add(macaquinhos);
                }
            }
        }
        catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
        return true;
    }

    public static LinkedList<Integer> transfArray(String[]  array){
        LinkedList<Integer> arrayInt = new LinkedList<>();
        for(int i = 0; i< numCoco; i++){
            arrayInt.add(Integer.parseInt(array[i]));
        }
        return arrayInt;
    }
}
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.nio.charset.Charset;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;

// public class App {

//     public static int rodada = 0;
//     public static int numCoco = 0;
//     public static List<Macaco> macacos = new ArrayList<>();

//     public static void main(String[] args) {
//         if (!readFile("caso0900.txt")) {
//             System.out.println("Erro ao ler arquivo");
//             return;
//         }

//         int vencedor = 0;
//         int maxCocos = 0;
        
//         long startTime = System.currentTimeMillis();
//         for(int i = 1; i <= rodada; i++) {
//             for (Macaco macaco : macacos) {
//                 int valorPar = macaco.getCocoPar() + macacos.get(macaco.getMandaPar()).getCocoPar();
//                 int valorImpar = macaco.getCocoImpar() + macacos.get(macaco.getMandaImpar()).getCocoImpar();
//                 macacos.get(macaco.getMandaPar()).setCocoPar(valorPar);
//                 macacos.get(macaco.getMandaImpar()).setCocoImpar(valorImpar);
//                 macaco.setCocoImpar(0);
//                 macaco.setCocoPar(0);
//             }
//         }

//         for (int i = 0; i < macacos.size(); i++) {
//             int cocoAtual = macacos.get(i).getTotal();
//             if (cocoAtual > maxCocos) {
//                 maxCocos = cocoAtual;
//                 vencedor = i;
//             }
//         }
//         long endTime = System.currentTimeMillis();
//         long executionTime = (endTime - startTime);
//         System.out.println("Execution time: " + executionTime + " miliseconds");

//         System.out.println("O vencedor é o Macaco " + vencedor + " com " + maxCocos + " cocos.");
//     }
    
//     public static LinkedList<Integer> transfArray(String[] arr) {
//         LinkedList<Integer> list = new LinkedList<Integer>();
//         for (String str : arr) {
//             list.add(Integer.parseInt(str));
//         }
//     return list;
//     }
    
//     public static boolean readFile(String nomeArq) {
//         Path path1 = Paths.get(nomeArq);
//         try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))) {
//             String line = null;
//             while ((line = reader.readLine()) != null) {
//                 if(!line.contains("Macaco")){
//                     int rodadas = Integer.parseInt(line.replaceAll("[^0-9]", ""));
//                     rodada = rodadas;
//                 }else{

//                     String[] dados1 = line.split(" : "); // dividir - "Macaco n par -> n impar -> n"  "numero de cocos" "cocos com quantidade de pedras dentro"
//                                                                             //    0                                 1                                2 
                    
//                     String[] dados2 = dados1[2].split(" ");//vetor dos cocos com pedras

//                     int numCocos = Integer.parseInt(dados1[1]); //transforma o dados1[1] em int
//                     numCoco = numCocos;

//                     LinkedList<Integer> cocos = transfArray(dados2);      //transforma a lista de cocos numa lista de cocos inteiros

//                     String regex = "[^\\d]+";
//                     String[] trocasMacacos = dados1[0].split(regex);//macacos que passara os cocos pares e impares
                    
//                     int numPar = 0;
//                     int numImpar = 0;
//                     for(int c: cocos){
//                         if(c%2==0){
//                             numPar++;
//                         }
//                         else{
//                             numImpar++;
//                         }
//                     }
//                                                         //par                               //impar
//                     Macaco macaquinhos = new Macaco(Integer.parseInt(trocasMacacos[2]), Integer.parseInt(trocasMacacos[3]), numPar, numImpar);
//                     macacos.add(macaquinhos);
//                 }
//             }
//         }
//         catch (IOException x) {
//             System.err.format("Erro de E/S: %s%n", x);
//         }
//         return true;
//     }

// }
