import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
public class App {
    private static int numCoco;
    private static LinkedList<Macaco> macacos = new LinkedList<>();
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
        int numPar;
        int numImpar;

        readFile("caso1000.txt");

        long startTime = System.currentTimeMillis();

        for(int r = 0; r <rodada; r++){
            for (Macaco m : macacos){
                numPar = m.getCocoPar() + macacos.get(m.getMandaPar()).getCocoPar(); //soma o tam de cocos par do macaco atual com o do macaco que tem que entregar os cocos par
                numImpar = m.getCocoImpar() + macacos.get(m.getMandaImpar()).getCocoImpar(); //soma o tam de cocos impar do macaco atual com o do macaco que tem que entregar os cocos impar
                
                macacos.get(m.getMandaPar()).setCocoPar(numPar); //seta o numero de cocos par do macaco par
                macacos.get(m.getMandaImpar()).setCocoImpar(numImpar); //seta o numero de cocos impar do macaco impar
                
                m.setCocoPar(0); //zera os cocos par do macaco atual
                m.setCocoImpar(0); //zera os cocos impar do macaco atual
            }
            System.out.println(r);
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
