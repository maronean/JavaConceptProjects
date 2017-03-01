/**
 * @author Andrew Marone
 * Parsed csv input and uses Naive Bayes classifier to predict
 * the classification of output
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Collections;


public class DefectPredictor {

    int [] significant = {0,13,17,18,19};
    int [] insignificant = {9,12,11,15};
    List<List<Double>> dataSet;
    public static void main(String[] args) {

     

        if(args.length > 0){
            String fileinput = args[0];
            DefectPredictor test = new DefectPredictor("defects.csv");

            List <List<List<Double>>> valSet = test.getValidationSet();
            test.crossValidation(valSet);
        }
        else{
            System.out.println("Please run with the name of csv file as an argument");
            System.out.println("Ex. java DefectPredictor defects.csv");
        }
        
    }

    /**
     * Constructor for main class
     * imports a dataset upon creation
     * @param fileName
     */
    public DefectPredictor(String fileName){
        this.dataSet = importData(fileName);
    }

    public List<List<Double>> getDataSet(){
        return this.dataSet;
    }

    /**
     * Reads csv file and counverts all values into Doubles
     * @param fileName
     * @return
     */
    public List<List<Double>> importData(String fileName) {
        BufferedReader reader = null;
        List <List<Double>> values = new ArrayList<List<Double>>();
        String line;
        try {

            reader = new BufferedReader(new FileReader(fileName));
            String header = reader.readLine();
            while((line = reader.readLine()) != null){
                String[] valStrings = line.split(",");
                List<Double> lineVals = new ArrayList<Double>();
                for(String val : valStrings){
                    lineVals.add(Double.parseDouble(val));

                }
                values.add(lineVals);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        } catch (IOException e) {

        }finally{
            if (reader != null) {
                try{
                    reader.close();
                }
                catch(IOException e){
                    System.out.print("Error closing file!");
                }
            }

        }
        return values;
    }

    /**
     * Extracts all the values of a particular featureset that share a specific classification
     * @param data - list of boolean arrays
     * @param id - the expected position of the desired feature
     * @param pos - boolean representing the requested classification.
     *            true = Defect Found
     *            false = No Defect
     * @return
     */
    public List<Double> getVars(List<List<Double>> data,int id,boolean pos){
        List<Double> vars = new ArrayList<Double>();
        if(pos) {
            for (List<Double> valList : data) {
                if (valList.get(valList.size() -1) == 1) {
                    vars.add(valList.get(id));
                }
            }
        }
        else{
            for (List<Double> valList : data) {
                if (valList.get(valList.size() -1) == 0) {
                    vars.add(valList.get(id));
                }
            }
        }
        return vars;
    }

    /**
     * Returns the median in a list of doubles
     * @param varList
     * @return
     */
    public double getMean(List<Double> varList){
        double sum = 0;
        for(Double i : varList){
            sum += i;
        }
        return sum/ varList.size();
    }

    /**
     * Gets the overall variance in a list of doubles
     * @param varList
     * @return
     */
    public double getVariance(List <Double> varList){
        double mean = getMean(varList);
        List <Double> vals = new ArrayList<Double>();
        for(Double i: varList){
            vals.add(Math.pow((i - mean),2));
        }
        return getMean(vals);
    }

    /**
     * The probability density of a particular feature within a data set
     * @param val
     * @param data
     * @return
     */
    public double getProbabilityDensity(double val,List <Double> data){
        //System.out.println("Get Probability Density");
        //System.out.println("Value: " +  val);
        //System.out.println("Variance: " +  getVariance(data));
        //System.out.println("Mean: " + getMean(data));
        double left = 1/(Math.sqrt(2*Math.PI)*Math.sqrt(getVariance(data)));
        double right = 0 - ((val - Math.pow(getMean(data),2)) / (2 * getVariance(data)));
        //System.out.println("Prob Density: " + Math.pow(left,right));
        return Math.pow(left,right);
    }

    /**
     * Finds the probability that a feature set is either a defect or not
     * @param testee  - list of doubles representing feature set
     * @param dataset - a list of lists representing preclassified data sets
     * @param ofDefects - dictates if results will indicate probability of failure or success
     * @return probability of outcome requested in param ofDefects
     */
    public double getProbability(List<Double> testee, List<List<Double>> dataset,boolean ofDefects){
        List<Double> probabs = new ArrayList<Double>();
        for(int id = 0; id < testee.size();id++){
            List <Double> vars = getVars(dataset,id, ofDefects);
            if(vars.size() > 0) {
                //System.out.println("Prob Density: " + getProbabilityDensity(testee.get(id), vars));
                Double probd = getProbabilityDensity(testee.get(id), vars);
                if(!probd.isNaN()) {
                    probabs.add(probd);
                    if(Arrays.asList(significant).contains(id)){
                        probabs.add(probd);
                    }
                    if(!Arrays.asList(insignificant).contains(id)){
                        probabs.add(probd);
                    }
                }
            }
        }
        double result = 1;
        for(Double i : probabs){
            result *= i;
        }
        return result;
    }

    /**
     * Predicts the class of the testee dataset
     * @param testee - list of doubles representing feature set
     * @param dataset - a list of lists representing preclassified data sets
     * @return a boolean representing the prediction results
     * true -> failure detected
     * false -> failure not detected
     */
    public boolean predictClass(List<Double> testee, List<List<Double>> dataset){
        double probDefect = getProbability(testee, dataset, true);
        double probNoDefect = getProbability(testee,dataset,false);
        //System.out.println(probDefect);
        //System.out.println(probDefect + " " + probNoDefect);
        return probDefect > probNoDefect;

    }

    /**
     * Randomizes a collection of observation sets and divides the collection into collections of 10
     * @param data
     * @return
     */
    public List <List<List<Double>>> getValidationSet(List <List<Double>> data){
        Collections.shuffle(data);
        List <List<List<Double>>> crossValList = new ArrayList <List<List<Double>>>();
        List <List<Double>> crossSet = new ArrayList <List<Double>>();
        int counter = 0;
        for(List<Double> dataSet : data){
            if( counter < 9){
                counter++;
                crossSet.add(dataSet);
            }
            else{
                counter = 0;
                crossSet.add(dataSet);
                crossValList.add(crossSet);
                crossSet = new ArrayList <List<Double>>();
            }
        }
        return crossValList;
    }

    public List <List<List<Double>>> getValidationSet(){
        List <List<List<Double>>> crossValList = getValidationSet(this.dataSet);
        return crossValList;
    }

    /**
     * Takes in a collection of Lists diveded into sets of 10
     * @param valSet
     */
    public void crossValidation(List <List<List<Double>>> valSet){
        int testeeID = 0;
        double tp = 0;
        double fp = 0;
        double tn = 0;
        double fn = 0;
        for(int i = 0; i < valSet.size();i++){
                List<List<Double>> curVals = valSet.get(i);
                List <Double> testee = curVals.remove(testeeID);
                boolean testeeClassTrue = (testee.remove(testee.size() - 1)) == 1;
                boolean testeeClassPredict = predictClass(testee,curVals);
                //System.out.println("actual val: " + testeeClassTrue);
                if(testeeClassPredict){
                    if(testeeClassTrue) tp++;
                    else fp++;
                }
                if(!testeeClassPredict){
                    if(!testeeClassTrue) tn++;
                    else fn++;
                }
        }

        double precision = tp / (tp + fp);
        double recall = tp / (tp + fn);
        double f1 = (2 * precision * recall) / (precision + recall);
        System.out.println("Results when positive = Failure Detected");
        System.out.println("TP: " +tp);
        System.out.println("FP: " +fp);
        System.out.println("TN: " + tn);
        System.out.println("FN: " + fn);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("f1: " + f1);

        precision = tn / (tn + fn);
        recall = tn / (tn + fp);
        f1 = (2 * precision * recall) / (precision + recall);
        System.out.println("Resutls when Positive = No Failure Detected");
        System.out.println("TP: " +tn);
        System.out.println("FP: " +fn);
        System.out.println("TN: " + tp);
        System.out.println("FN: " + fp);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("f1: " + f1);
    }

    /*
    A simple discretization method
    with a data set and identified feature returns the mean value
    future values can be discretized as above or below the mean value
     */
    public double getDiscretization(List<List<Double>> data,int id){
        List <Double> vars = new ArrayList<Double>();
        for (List<Double> valList : data) {

                vars.add(valList.get(id));

        }
        return getMean(vars);
    }

}
