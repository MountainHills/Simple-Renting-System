package parameters;

public class RentInformation 
{
    
    private static final int WATER_RATE = 150;
    private static final int ELECTRICITY_RATE = 24; 
    
    private static String name;
    private static float previousElectricity;
    private static float currentElectricity;
    private static float members;
    private static float rent;
   
    private static float electricDifference;
    private static float electricityTotal;
    private static float waterTotal;
    private static float totalBill;

    public RentInformation() {
    }
    
    public static void processInformation()
    {
        electricDifference = currentElectricity - previousElectricity;
        electricityTotal= electricDifference * ELECTRICITY_RATE;
        waterTotal = members * WATER_RATE;
        totalBill = electricityTotal + waterTotal + rent;
    }
    
    public static void setName(String name) {
        RentInformation.name = name;
        
        if (RentInformation.name.equals("")) throw new NullPointerException();
    }

    public static void setPreviousElectricity(float previousElectricity) throws NumberFormatException {
        RentInformation.previousElectricity = previousElectricity;
    }

    public static void setCurrentElectricity(float currentElectricity) throws NumberFormatException {
        RentInformation.currentElectricity = currentElectricity;
        
        if (previousElectricity > currentElectricity) throw new ArithmeticException();
    }

    public static void setMembers(float members) throws NumberFormatException {
        RentInformation.members = members;
    }

    public static void setRent(float rent) throws NumberFormatException {
        RentInformation.rent = rent;
    }

    public static float getTotalBill() {
        return totalBill;
    }

    public static String getName() {
        return name;
    }

    public static float getPreviousElectricity() {
        return previousElectricity;
    }

    public static float getCurrentElectricity() {
        return currentElectricity;
    }

    public static float getMembers() {
        return members;
    }

    public static float getRent() {
        return rent;
    }
}
