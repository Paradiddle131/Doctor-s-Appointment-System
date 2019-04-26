/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointment;

/**
 *
 * @author User
 */
public class PatientObject implements Patient{

    private int patientID;
    private String name, surname;

    public PatientObject() 
    {
        
    }
    
    public PatientObject(int id, String n, String s) 
    {
        patientID = id;
        name = n;
        surname = s;
    }
    
    
    
    @Override
    public int getID() {
        return patientID;
    }

    @Override
    public void printID() {
        System.out.println("    " + patientID + "\n    " + name + "\n    " + surname);
        
    }
    
}
