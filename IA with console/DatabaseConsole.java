import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
/**
 * A console interface for <code>Database</code>.
 * 
 * @author  
 * @version (1.0.1)
 */
public class DatabaseConsole
{
    // class variables
    public static enum LastAction
    {
        ADD_JOB,
        ADD_JOBS_FROM_FILE,
        ADD_JOBS_FROM_FILE_UNSUCCESSFUL,
        ADD_VOLUNTEER,
        ADD_VOLUNTEERS_FROM_FILE,
        ADD_VOLUNTEERS_FROM_FILE_UNSUCCESSFUL,
        ASSIGN_VOLUNTEERS_TO_JOBS,
        DELETE_JOB,
        DELETE_VOLUNTEER,
        FILE_NOT_FOUND,
        INVALID_INPUT,
        SAVE_JOB_LIST,
        SAVE_VOLUNTEER_LIST,
        NONE
    } // end of enum LastAction
    private static final int[] OPTION = {0,1,2,3,4,5,6,7,8,9,10,11}; 

    // instance variables 
    private BufferedReader consoleReader;
    private Database database1;
    private String input;
    private boolean inputIsValid;
    private LastAction lastAction;
    int[] validInputOption; 
    public DatabaseConsole()
    {
        consoleReader = new BufferedReader (new InputStreamReader(System.in)); 
        database1 = new Database();
        inputIsValid = false;
        lastAction = LastAction.NONE;
        validInputOption = new int[0];
    } // end of constructor DatabaseConsole()

    public static void main(String[] arguments) throws IOException
    {
        DatabaseConsole databaseConsole1 = new DatabaseConsole();
        databaseConsole1.addAllJobs();
        databaseConsole1.addAllVolunteers();
    } // end of main method

    /**
     * Returns the input from user of this console.
     * 
     * @return the input from user of this console
     */
    public String getInput()
    {
        return input;
    } // end of accessor getInput()

    /**
     * Returns the validity of the input from user of this console.
     * 
     * @return the validity of the input from user of this console
     */
    public boolean getInputIsValid()
    {
        return inputIsValid;
    } // end of accessor getInputIsValid()

    /**
     * Returns the last action done by this console.
     * 
     * @return the last action done by this console
     */
    public LastAction getLastAction()
    {
        return lastAction;
    } // end of accessor getLastAction()

    /**
     * Returns the valid input from user options of this console.
     * 
     * @return the valid input from user options of this console
     */
    public int[] getValidInputOption()
    {
        return validInputOption;
    } // end of accessor getValidInputOption()

    /**
     * Sets the input from user of this console.
     * 
     * @param input the input from user of this console <br>
     * <i>precondition:</i> <code>input</code> cannot be null
     */
    public void setInput(String input)
    {
        if (input != null)
        {
            this.input = input;
        } // end of if (input != null)
    } // end of mutator setInput(String input)

    /**
     * Sets the validity of the input from user of this console.
     * 
     * @param inputIsValid the validity of the input from user of this console
     */
    public void setInputIsValid(boolean inputIsValid)
    {
        this.inputIsValid = inputIsValid;
    } // end of mutator setInputIsValid(boolean inputIsValid)

    /**
     * Sets the last action done by this console.
     * 
     * @param lastAction the last action done by this console
     */
    public void setLastAction(LastAction lastAction)
    {
        if (lastAction != lastAction)
        {
            this.lastAction = lastAction;
        } // end of if (lastAction != lastAction)
    } // end of mutator setLastAction(LastAction lastAction)

    /**
     * Sets the valid input from user options of this console.
     * 
     * @param validInputOption the valid input from user options of this console <br>
     * <i>precondition:</i> <code>validInputOption</code> cannot be null
     */
    public void setValidInputOption(int[] validInputOption)
    {
        if (validInputOption != null)
        {
            this.validInputOption = validInputOption;
        } // end of if (validInputOption != null)
    } // end of mutator setValidInputOption(int[] validInputOption)

    /**
     * Adds jobs from the given file.
     * 
     * @param filePath the file path of the file <br>
     * <i>precondition:</i> <code>filePath</code> cannot be null
     */
    public void addJobsFromFile(String filePath) throws IOException
    {
        if (filePath != null)
        {
            try
            {
                BufferedReader fileReader = new BufferedReader(new FileReader(filePath)); 
                String concatenatedFileData = "";
                boolean fileIsCorrupt = false;
                boolean isEndOfFile = false;
                String fileData;
                while(!isEndOfFile)
                {
                    fileData = fileReader.readLine();
                    if (fileData == null)
                        isEndOfFile = true;
                    if (!isEndOfFile)
                    {
                        concatenatedFileData += fileData;
                    } // end of if (!isEndOfFile)
                } // end of while(!isEndOfFile)
                final String SPLIT_STRING = ",";
                String[] job = concatenatedFileData.split(SPLIT_STRING);
                for (int numberOfWorkersNeededIndex = 1; numberOfWorkersNeededIndex < job.length; numberOfWorkersNeededIndex+=2)
                {
                    try 
                    {
                        int numberOfWorkersForEachJob = Integer.parseInt(job[numberOfWorkersNeededIndex]);
                    }
                    catch (NumberFormatException error)
                    {
                        fileIsCorrupt = true;
                        lastAction = LastAction.ADD_JOBS_FROM_FILE_UNSUCCESSFUL;
                    } // end of try
                } // end of for (int numberOfWorkersNeededIndex = 1; numberOfWorkersNeededIndex < job.length; numberOfWorkersNeededIndex+=2)
                if (!fileIsCorrupt)
                {
                    for (int jobIndex = 0; jobIndex < job.length; jobIndex+=2)
                    {
                        database1.addJob((job[jobIndex]), Integer.parseInt(job[jobIndex+1]));
                    } // end of for(int jobIndex = 0; jobIndex < job.length; jobIndex++)
                    lastAction = LastAction.ADD_JOBS_FROM_FILE;
                } // end of if (!fileIsCorrupt)

            }
            catch(FileNotFoundException error)
            {
                lastAction = LastAction.FILE_NOT_FOUND;
            } // end of try
        } // end of if (filePath != null)
    } // end of method addJobsFromFile(String filePath) 

    /**
     * Adds volunteers from the given file.
     * 
     * @param filePath the file path of the file <br>
     * <i>precondition:</i> <code>filePath</code> cannot be null
     */
    public void addVolunteersFromFile(String filePath) throws IOException
    {
        if (filePath != null)
        {
            try
            {
                BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
                String concatenatedFileData = "";
                String fileData;
                boolean isEndOfFile = false;
                while(!isEndOfFile)
                {
                    fileData = fileReader.readLine();
                    if (fileData == null)
                        isEndOfFile = true;
                    if (!isEndOfFile)
                    {
                        concatenatedFileData += fileData;
                    } // end of if (!isEndOfFile)
                } // end of while(!isEndOfFile)
                final String SPLIT_STRING = ",";
                String[] datum = concatenatedFileData.split(SPLIT_STRING);
                boolean badFormat = false;
                final int FALSE = 0;
                final int TRUE = 1;
                for (int datumIndex = 0; datumIndex < datum.length; datumIndex = datumIndex + 2 + database1.getJobList().size()) 
                {
                    for (int canDoJobIndex = datumIndex + 2; canDoJobIndex < datumIndex + 2 + database1.getJobList().size(); canDoJobIndex++)
                    {
                        try
                        {
                            int option = Integer.parseInt(datum[canDoJobIndex]);
                            if (option != FALSE && option != TRUE)
                            {
                                badFormat = true;
                                lastAction = LastAction.ADD_VOLUNTEERS_FROM_FILE_UNSUCCESSFUL;
                            } // end of if (option != FALSE || option != TRUE)
                        }

                        catch (NumberFormatException error)
                        {
                            badFormat = true;
                            lastAction = LastAction.ADD_VOLUNTEERS_FROM_FILE_UNSUCCESSFUL;
                        } // end of try
                    } // end of for (int canDoJobIndex = datumIndex + 2; canDoJobIndex < datumIndex + 2 + database1.getJobList().size(); canDoJobIndex++)
                } // end of for (int datumIndex = 0; datumIndex < datum.length; datumIndex = datumIndex + 2 + database1.getJobList().size()) 

                if (!badFormat)
                {
                    lastAction = LastAction.ADD_VOLUNTEERS_FROM_FILE;
                    for (int datumIndex = 0; datumIndex < datum.length; datumIndex = datumIndex + 2 + database1.getJobList().size()) 
                    {
                        boolean[] jobsCanDo = new boolean[database1.getJobList().size()];
                        int jobsCanDoIndex = 0;
                        for (int canDoJobIndex = datumIndex + 2; canDoJobIndex < datumIndex + 2 + database1.getJobList().size(); canDoJobIndex++)
                        {
                            try
                            {
                                int option = Integer.parseInt(datum[canDoJobIndex]);
                                if (option == FALSE)
                                {
                                    jobsCanDo[jobsCanDoIndex] = false;
                                } // end of if (option == FALSE)
                                if (option == TRUE)
                                {
                                    jobsCanDo[jobsCanDoIndex] = true;
                                } // end of if (option == TRUE)
                                jobsCanDoIndex +=1;
                            } 
                            catch (NumberFormatException error)
                            {
                            } // end of try

                        } // end of for (int canDoJobIndex = datumIndex + 2; canDoJobIndex < datumIndex + 2 + database1.getJobList().size(); canDoJobIndex++)
                        database1.addVolunteer(datum[datumIndex], datum[datumIndex+1], jobsCanDo);
                    } // end of for (int datumIndex = 0; datumIndex < datum.length; datumIndex = datumIndex + 2 + database1.getJobList().size()) 
                } // end of  if (!badFormat)
            }
            catch(FileNotFoundException error)
            {
                lastAction = LastAction.FILE_NOT_FOUND;
            } // end of try
        } // end of if (filePath != null)
    } // end of addVolunteersFromFile(String filePath) throws IOException

    /**
     * Prints out in the console the list of volunteers and their actual jobs.
     */
    public void displayActualJobsOfVolunteers()
    {
        for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
        {
            int volunteerNumber = volunteerIndex + 1;
            System.out.println(volunteerNumber + ": " + database1.getVolunteerList().get(volunteerIndex).getNameAndActualJobTitle());
        } // end of for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
    } // end of displayActualJobsOfVolunteers()

    /**
     * Prints out in the console the current jobs added.
     */
    public void displayCurrentJobs()
    {
        for (int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
        {
            int jobNumber = jobIndex + 1;
            System.out.println(jobNumber + ": " + database1.getJobList().get(jobIndex).getJobTitle() + " - " + database1.getJobList().get(jobIndex).getNumberOfVolunteersNeeded() + " volunteers required.");
        } // end of for (int jobIndex = 0; jobIndex < job.size(); jobIndex++)
    } // end of displayCurrentJobs()

    /**
     * Prints out in the console the current volunteers added and the jobs they are capable of doing.
     */
    public void displayCurrentVolunteers()
    {
        for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
        {
            int volunteerNumber = volunteerIndex + 1;
            System.out.println(volunteerNumber + ": " + database1.getVolunteerList().get(volunteerIndex).getNameAndPotentialJobTitles());
        } // end of for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
    } // end of displayCurrentVolunteers()

    /**
     * Prints out in the console the jobs that need volunteers, and the number of volunteers each job requires, in order from jobs that need the most volunteers to jobs that need the least volunteers.
     */
    public void displayExtraJobs()
    {
        System.out.println("\n");
        database1.bubbleSortJobList(database1.getJobList());
        int jobIndex = database1.getJobList().size()-1;
        boolean jobIndexIsNegative = false; 
        while (!jobIndexIsNegative && database1.getJobList().get(jobIndex).getNumberOfVolunteersNeeded() != 0)
        {
            int jobNumber = jobIndex + 1;
            System.out.println(jobNumber + ": " + database1.getJobList().get(jobIndex).getJobTitle() + " - " + database1.getJobList().get(jobIndex).getNumberOfVolunteersNeeded() +
                " volunteers needed");
            jobIndex--;
            if (jobIndex == - 1)
            {
                jobIndexIsNegative = true;
            } // end of if (jobIndex == - 1)
        } // end of while(!jobIndexIsNegative && database1.getJobList().get(jobIndex).getNumberOfVolunteersNeeded() != 0)
    } // end of displayExtraJobs()

    /**
     * Prints out the information of the job of the given job title. 
     * 
     * @param jobTitle the family name of the volunteer desired <br>
     * <i>precondition:</i> <code>jobTitle</code> cannot be null
     */
    public void displayGivenJob(String jobTitle)
    {
        if (jobTitle != null)
        {
            boolean jobFound = false;
            for (int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
            {
                if (database1.getJobList().get(jobIndex).getJobTitle().equals(jobTitle))
                {
                    System.out.println(database1.getJobList().get(jobIndex).toString());
                    jobFound = true;
                } // end of if (database1.getJobList().get(jobIndex).getJobTitle().equals(jobTitle))
            } // end of for (int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
            // end of if (jobTitle != null)
            if (!jobFound)
            {
                System.out.println("Job not found!");
            } // end of if (!jobFound)
        } // end of if (jobTitle != null)
    } // end of displayGivenJob(String jobTitle)

    /**
     * Prints out the information of the volunteer of the given family and given name. 
     * 
     * @param familyName the family name of the volunteer desired <br>
     * <i>precondition:</i> <code>familyName</code> cannot be null
     * @param givenName the givenName of the volunteer desired <br>
     * <i>precondition:</i> <code>givenName</code> cannot be null
     */
    public void displayGivenVolunteer(String familyName, String givenName)
    {
        if (familyName != null && givenName != null)
        {
            boolean volunteerFound = false;
            for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
            {
                if (database1.getVolunteerList().get(volunteerIndex).getFamilyName().equals(familyName) && database1.getVolunteerList().get(volunteerIndex).getGivenName().equals(givenName))
                {
                    System.out.println(database1.getVolunteerList().get(volunteerIndex).toString());
                    volunteerFound = true;
                } // end of if (database1.getVolunteerList().get(volunteerIndex).getFamilyName().equals(familyName) && database1.getVolunteerList().get(volunteerIndex).getGivenName().equals(givenName))
            } // end of for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
            if (!volunteerFound)
            {
                System.out.println("Volunteer not found!");
            } // end of if (!volunteerFound)
        } // end of if (familyName != null && givenName != null)
    } // end of displayGivenVolunteer(String familyName, String givenName)

    /**
     * Prints out in the console the last action done.
     * 
     * @param lastAction the last action done <br>
     * <i>precondition:</i> <code>lastAction</code> cannot be null
     */
    public void displayLastAction(LastAction lastAction)
    {
        if (lastAction != null)
        {
            switch (lastAction)
            {
                case ADD_JOB:
                System.out.println("You have added a job.");
                break;

                case ADD_JOBS_FROM_FILE:
                System.out.println("You have added jobs from a file.");
                break;

                case ADD_JOBS_FROM_FILE_UNSUCCESSFUL:
                System.out.println("Jobs were unable to be extracted from the file as the file data format was incorrect.");
                break;

                case ADD_VOLUNTEER:
                System.out.println("You have added a volunteer.");
                break;

                case ADD_VOLUNTEERS_FROM_FILE:
                System.out.println("You have added volunteers from a file.");
                break;

                case ADD_VOLUNTEERS_FROM_FILE_UNSUCCESSFUL:
                System.out.println("Volunteers were unable to be extracted from the file as the file data format was incorrect.");
                break;

                case ASSIGN_VOLUNTEERS_TO_JOBS:
                System.out.println("You have assigned volunteers to jobs.");
                break;

                case DELETE_JOB:
                System.out.println("You have deleted a job.");
                break;

                case DELETE_VOLUNTEER:
                System.out.println("You have deleted a volunteer.");
                break;

                case FILE_NOT_FOUND:
                System.out.println("Invalid Action. File not found.");
                break;

                case INVALID_INPUT:
                System.out.println("You have typed an invalid input. Please try again.");
                break;

                case SAVE_JOB_LIST:
                System.out.println("You have successfully saved the job list.");
                break;

                case SAVE_VOLUNTEER_LIST:
                System.out.println("You have successfully saved the volunteer list.");
                break;

                default:
                break;
            } // end of switch(lastAction)
            System.out.println("**********************");
        } // end of if (lastAction != null)
    } // end of displayLastAction(LastAction lastAction)

    /**
     * Prints out in the console the list of actual jobs having the names of the volunteers of each job below each job. 
     */
    public void displayJobsAndTheirVolunteers()
    {
        for(int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
        {
            int jobNumber = jobIndex + 1;
            System.out.println(jobNumber + ": " + database1.getJobList().get(jobIndex).getJobTitle());            
            for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
            {
                if (database1.getJobList().get(jobIndex) == database1.getVolunteerList().get(volunteerIndex).getActualJob())
                {
                    System.out.println("\t" + database1.getVolunteerList().get(volunteerIndex).getGivenName() + " " + database1.getVolunteerList().get(volunteerIndex).getFamilyName());
                } // end of if (database1.getJobList().get(jobIndex) == database1.getVolunteerList().get(volunteerIndex).getActualJob())
            } // end of for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
        } // end of for(int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
    } // end of displayJobsOfVolunteers()

    /**
     * Prints out in the console the volunteers that need to be trained.
     */
    public void displayVolunteersWithoutJobs()
    {
        final String NO_JOB = "no job. needs to be trained!";
        for( int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
        {
            if (database1.getVolunteerList().get(volunteerIndex).getActualJob().getJobTitle().equals(NO_JOB))
            {
                System.out.println(database1.getVolunteerList().get(volunteerIndex).toString());
            } // end of  if (database1.getVolunteerList().get(volunteerIndex).getActualJob().getJobTitle().equals(NO_JOB))
        } // end of for( int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
    } // end of displayVolunteersWithoutJobs()

    /**
     * Terminates the program.
     */
    public void exit()
    {
        System.out.println("\f");
        System.out.println("Have a nice day.");
        System.exit(0);
    } // end of exit()

    /**
     * Asks the user via console if the given volunteer can do the job at the given job index. Then sets the answer in the given volunteers potential jobs list. 
     * 
     * @param volunteer the volunteer asked <br>
     * <i>precondition:</i> <code>volunteer</code> cannot be null
     * @param jobIndex the job index of the job the volunteer is asked about 
     */
    public void findOutIfTheVolunteerCanDoJob(Volunteer volunteer, int jobIndex) throws IOException
    {
        if (volunteer != null)
        {           
            System.out.println("Can the volunteer be a " + database1.getJobList().get(jobIndex).getJobTitle() + "?");
            System.out.println("Type \"1\" for yes, Type \"2\" for no.");
            input = consoleReader.readLine();
            inputIsValid = true;
            final int INVALID_OPTION_INDEX = -1;
            int option = INVALID_OPTION_INDEX;
            final int VALID_OPTION_1 = 1;
            final int VALID_OPTION_2 = 2;
            if (input!= null)
            {
                try 
                {
                    option = Integer.parseInt(input);
                } 
                catch (NumberFormatException error)
                {
                    inputIsValid = false;
                    lastAction = LastAction.INVALID_INPUT;
                } // end of try
                if (option != VALID_OPTION_1 && option != VALID_OPTION_2)
                {
                    inputIsValid = false;
                    lastAction = LastAction.INVALID_INPUT;
                } // end of if (option != VALID_OPTION_1 && option != VALID_OPTION_2)
                if (inputIsValid)
                {
                    if (option == VALID_OPTION_1)
                    {
                        volunteer.getCanDoJob()[jobIndex] = true;
                        volunteer.updateNumberOfJobsCanDo();
                    }
                    else 
                    {
                        volunteer.getCanDoJob()[jobIndex]= false;
                    } // end of if (option == VALID_OPTION_1)
                }  // end of if (inputIsValid)
            } // end of if (input!= null)
        } // end of if (volunteer != null)
    } // end of findOutIfTheVolunteerCanDoJob(Volunteer volunteer, int jobIndex)

    /**
     * Receives the console input and the list of valid console inputs and invokes appropriate action.
     * 
     * @param input the console input <br>
     * <i>precondition:</i> <code>input</code> cannot be null
     */
    public void manageConsoleInput(String input,int[] validOption) throws IOException
    {
        if (input != null)
        {
            inputIsValid = false;
            lastAction = LastAction.INVALID_INPUT;
            final int INVALID_OPTION_INDEX= Integer.MAX_VALUE;
            int option = INVALID_OPTION_INDEX;
            try 
            {
                option = Integer.parseInt(input);
            }
            catch (NumberFormatException error)
            {
                if (database1.getVolunteerList().size()== 0)
                {
                    addAllJobs();
                }
                else
                {
                    addAllVolunteers();
                } // end of if (database1.getVolunteerList().size()== 0)
            } // end of try

            for (int i = 0 ; i < validOption.length; i++)
            {
                if (validOption[i] == option)
                {
                    inputIsValid = true;
                } // end of if (validOption[i] == option)
            } // end of for (int i = 0 ; i < validOption.length; i++)
            if (!inputIsValid && database1.getVolunteerList().size()== 0)
            {
                addAllJobs();
            }
            if (!inputIsValid && database1.getVolunteerList().size() != 0)
            {
                addAllVolunteers();
            } //end of if (!inputIsValid && database1.getVolunteerList().size() != 0)

            if (inputIsValid)
            {
                switch (option)
                {
                    case 0:
                    exit();
                    break;

                    case 1:
                    System.out.println("Please input the file path of the file.");
                    String text = consoleReader.readLine();
                    addJobsFromFile(text);
                    addAllJobs();
                    break;

                    case 2:
                    System.out.println("What is the job title of the job you would like to add?");
                    String jobTitle = consoleReader.readLine();
                    System.out.println("How many workers does this job need?");
                    String numberOfWorkersNeeded = consoleReader.readLine();
                    final int INVALID_NUMBER_OF_WORKERS_REQUIRED = -1;
                    lastAction = LastAction.INVALID_INPUT;
                    boolean parseSuccesful = true;
                    int numberOfWorkersRequired = INVALID_NUMBER_OF_WORKERS_REQUIRED;
                    try 
                    {
                        numberOfWorkersRequired = Integer.parseInt(numberOfWorkersNeeded);
                    }

                    catch (NumberFormatException error)
                    {
                        parseSuccesful = false;
                    } // end of try

                    if (parseSuccesful)
                    {
                        if (database1.addJob(jobTitle, numberOfWorkersRequired))
                        {
                            lastAction = LastAction.ADD_JOB;
                        } // end of if (database1.addJob(jobTitle, numberOfWorkersRequired))
                    } // end of if (parseSuccesful)
                    addAllJobs();
                    break;

                    case 3:
                    System.out.println("\f");
                    lastAction = LastAction.INVALID_INPUT;
                    if (database1.getJobList().size() != 0)
                    {
                        System.out.println("Type the job number of the job would you like to delete.");
                        displayCurrentJobs();
                        String givenJobDeleteNumber = consoleReader.readLine();
                        final int INVALID_JOB_DELETE_INDEX = -1;
                        int jobDeleteIndex = INVALID_JOB_DELETE_INDEX;
                        parseSuccesful = true;
                        try 
                        {
                            jobDeleteIndex = Integer.parseInt(givenJobDeleteNumber) - 1;
                        } 

                        catch (NumberFormatException error)
                        {
                            parseSuccesful = false;
                        } // end of try

                        if (parseSuccesful)
                        {
                            if (database1.deleteJob(jobDeleteIndex))
                            {
                                lastAction = LastAction.DELETE_JOB;
                            } // end of if (database1.deleteJob(jobDeleteIndex))
                        } // end of if (parseSuccesful)
                    } // end of if (job.size() != 0)
                    addAllJobs();
                    break;

                    case 4:
                    System.out.println("Please input the file path of the file.");
                    text = consoleReader.readLine();
                    addVolunteersFromFile(text);
                    addAllVolunteers();
                    break;

                    case 5:
                    lastAction = LastAction.INVALID_INPUT;
                    if (database1.getJobList().size()!=0)
                    {

                        System.out.println("What is the given name of the volunteer you would like to add?");
                        String givenName = consoleReader.readLine();
                        System.out.println("What is the family name of the volunteer you would like to add?");
                        String familyName = consoleReader.readLine();
                        if (database1.addVolunteer(familyName, givenName))
                        {  
                            lastAction = LastAction.ADD_VOLUNTEER;
                            int jobIndex = 0;
                            while (jobIndex < database1.getJobList().size() && lastAction != LastAction.INVALID_INPUT)
                            {
                                findOutIfTheVolunteerCanDoJob(database1.getVolunteerList().get(database1.getVolunteerList().size()-1),jobIndex);
                                jobIndex++;
                            } // end of while (jobIndex < database1.getJobList().size() && lastAction != LastAction.INVALID_INPUT)
                            if (lastAction == LastAction.INVALID_INPUT)
                            {
                                database1.deleteVolunteer(database1.getVolunteerList().size()-1);
                            } // end of if (lastAction == LastAction.INVALID_INPUT)
                        } // end of if (addVolunteer(familyName, givenName))
                        addAllVolunteers();
                    }
                    else
                    {
                        lastAction = LastAction.INVALID_INPUT;
                        addAllJobs();
                    } // end of if (job.size()!=0)
                    break;

                    case 6:
                    System.out.println("\f");
                    lastAction = LastAction.INVALID_INPUT;
                    if (database1.getVolunteerList().size()!=0)
                    {                     
                        System.out.println("Type the volunteer number of the job would you like to delete.");
                        displayCurrentVolunteers();
                        String givenVolunteerDeleteNumber = consoleReader.readLine();
                        final int INVALID_VOLUNTEER_DELETE_INDEX = -1;
                        int volunteerDeleteIndex = INVALID_VOLUNTEER_DELETE_INDEX;
                        parseSuccesful = true;

                        try 
                        {
                            volunteerDeleteIndex = Integer.parseInt(givenVolunteerDeleteNumber) - 1;
                        }
                        catch (NumberFormatException error)
                        {
                            parseSuccesful = false;
                        } // end of try

                        if (parseSuccesful)
                        {
                            if (database1.deleteVolunteer(volunteerDeleteIndex))
                            {
                                lastAction = LastAction.DELETE_VOLUNTEER;
                            } // end of if (database1.deleteVolunteer(volunteerDeleteIndex))
                        } // end of if (parseSuccesful)
                        addAllVolunteers();
                    }
                    else
                    {
                        addAllJobs();
                    } // end of if (database1.getVolunteerList().size()!=0)
                    break;

                    case 7:
                    if (database1.getJobList().size() == 0)
                    {
                        lastAction = LastAction.INVALID_INPUT;
                        addAllJobs();                       
                    } // end of if (database1.getJobList().size() == 0)
                    else if (database1.getVolunteerList().size() == 0)
                    {
                        lastAction = LastAction.INVALID_INPUT;
                        addAllVolunteers();      
                    } // end of else if (database1.getVolunteerList().size() == 0)
                    else
                    {
                        if (!database1.getJobsHaveBeenAssigned())
                            database1.assignJobsToVolunteers();
                        System.out.println("\f");
                        System.out.println("\nTo exit type \"0\".");
                        System.out.println("To display the job of each volunteer type \"7\".");
                        System.out.println("To display the volunteers of each job type \"8\".");
                        displayActualJobsOfVolunteers();
                        displayExtraJobs();
                        input = consoleReader.readLine();
                        validInputOption = new int[]{OPTION[0],OPTION[7],OPTION[8]};
                        manageConsoleInput(input, validInputOption);
                    } // end of if (database1.getJobList().size() == 0)

                    break;

                    case 8:
                    System.out.println("\f");
                    System.out.println("\nTo exit type \"0\".");
                    System.out.println("To display the job of each volunteer type \"7\".");
                    System.out.println("To display the volunteers of each job type \"8\".");
                    displayJobsAndTheirVolunteers();
                    displayExtraJobs();
                    input = consoleReader.readLine();
                    validInputOption = new int[] {OPTION[0], OPTION[7], OPTION[8]};
                    manageConsoleInput(input, validInputOption);
                    break;

                    case 9:
                    if (database1.getJobList().size() != 0)
                    {
                        System.out.println("Please input the file path of the file you wish to save the job list to.");
                        input = consoleReader.readLine();
                        writeJobListToFile(new File(input));
                    } 
                    else
                    {
                        lastAction = LastAction.INVALID_INPUT;
                    } // end of if (database1.getJobList().size() != 0)
                    if (database1.getVolunteerList().size() == 0)
                    {
                        addAllJobs();
                    }
                    else
                    {
                        addAllVolunteers();
                    } // end of if (database1.getVolunteerList().size() != 0)
                    break;

                    case 10:
                    if (database1.getVolunteerList().size() != 0)
                    {
                        System.out.println("Please input the file path of the file you wish to save the volunteer list to.");
                        input = consoleReader.readLine();
                        writeVolunteerListToFile(new File(input));
                    }
                    else
                    {
                        lastAction = LastAction.INVALID_INPUT;
                    } // end of if (database1.getVolunteerList().size() != 0)
                    if (database1.getVolunteerList().size() == 0)
                    {
                        addAllJobs();
                    }
                    else
                    {
                        addAllVolunteers();
                    } // end of if (database1.getVolunteerList().size() != 0)
                    break;

                    case 11:
                    lastAction = LastAction.NONE;
                    addAllVolunteers();
                    break;

                    default:
                    break;
                } // end of switch (option)
            } // end of if (inputIsValid)
        } // end of if (volunteer != null)
    } // end of findOutIfTheVolunteerCanDoJob(Volunteer volunteer, int jobIndex) throws IOException

    /**
     * Print outs in the console the user interface for when the user is adding jobs. 
     */
    public void showConsoleDisplayForAddingJobs()
    {
        System.out.println("You have added " + database1.getJobList().size() + " jobs.");
        System.out.println("Current Added Jobs:");
        displayCurrentJobs();
        System.out.println("\nTo exit type \"0\".");
        System.out.println("To add jobs from a file type \"1\".");
        System.out.println("To add another job type \"2\".");
        System.out.println("To delete a job type \"3\".");
        System.out.println("To save the job list to a file type \"9\".");
        System.out.println("To save the volunteer list to a file type \"10\".");
        System.out.println("To start adding volunteers type \"11\".");
    } // end of showConsoleDisplayForAddingJobs()

    /**
     * Print outs in the console the user interface for when the user is adding volunteer. 
     */
    public void showConsoleDisplayForAddingVolunteers()
    {
        System.out.println("You have added " + database1.getJobList().size() + " jobs.");
        System.out.println("Current Added Jobs:");
        displayCurrentJobs();
        System.out.println("\nYou have added " + database1.getVolunteerList().size() + " volunteers");
        System.out.println("Current Added Volunteers:");
        displayCurrentVolunteers();
        System.out.println("\nYou cannot add anymore jobs.");
        System.out.println("\nTo exit type \"0\".");
        System.out.println("To add volunteers from a file type \"4\".");
        System.out.println("To add another volunteer type \"5\".");
        System.out.println("To delete a volunteer type \"6\".");
        System.out.println("To assign jobs to volunteers type \"7\".");
        System.out.println("To save the job list to a file type \"9\".");
        System.out.println("To save the volunteer list to a file type \"10\".");
    } // end of showConsoleDisplayForAddingVolunteers()

    /**
     * Prints out in the console the instructions for using the program.
     */
    public void showInstructions()
    {
        System.out.println("\f");
        System.out.println("Welcome to the job assigner!");
        System.out.println("First add all the jobs. Then, in order from highest priority to lowest priority, input your volunteers with the jobs they can do.");
        System.out.println("With that information, the job assigner will assign jobs to volunteers to maximize the number of volunteers with jobs!");
        System.out.println("**********************");
    } // end of showInstructions()

    /**
     * Writes the job list to the given file.
     * 
     * @param file the file written in <br>
     * <i>precondition:</i> <code>file</code> cannot be null
     */
    public void writeJobListToFile(File file) throws IOException
    {
        if (file != null)
        {
            boolean writeSuccessful = true;
            final String SPLIT_DELIMITER = ",";
            try
            {
                String jobList = "";
                for (int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
                {
                    jobList = jobList + database1.getJobList().get(jobIndex).getJobTitle() + SPLIT_DELIMITER
                    + database1.getJobList().get(jobIndex).getNumberOfVolunteersNeeded() + SPLIT_DELIMITER;
                } // end of for (int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
                PrintWriter fileWriter = new PrintWriter(file);
                fileWriter.println(jobList);
                fileWriter.close();
            }
            catch (FileNotFoundException error)
            {
                lastAction = LastAction.FILE_NOT_FOUND;
                writeSuccessful = false;
            } // end of try
            if (writeSuccessful)
                lastAction = LastAction.SAVE_JOB_LIST;
        } // end of if (file != null)
    } // end of method writeToFile()

    /**
     * Writes the volunteer list to the given file.
     * 
     * @param file the file written in <br>
     * <i>precondition:</i> <code>file</code> cannot be null
     */
    public void writeVolunteerListToFile(File file) throws IOException
    {
        if (file != null)
        {
            boolean writeSuccessful = true;
            try
            {
                final int CAN_DO_JOB = 1;
                final int CANNOT_DO_JOB = 0;
                final String SPLIT_DELIMITER = ",";
                String volunteerList = "";
                for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
                {
                    volunteerList = volunteerList + database1.getVolunteerList().get(volunteerIndex).getFamilyName() + SPLIT_DELIMITER 
                    + database1.getVolunteerList().get(volunteerIndex).getGivenName() + SPLIT_DELIMITER;
                    String jobsCanDo = "";
                    for (int jobIndex = 0; jobIndex < database1.getJobList().size(); jobIndex++)
                    {
                        if (database1.getVolunteerList().get(volunteerIndex).getCanDoJob()[jobIndex])
                        {
                            jobsCanDo = jobsCanDo + CAN_DO_JOB + SPLIT_DELIMITER;
                        } 
                        else 
                        {
                            jobsCanDo = jobsCanDo + CANNOT_DO_JOB + SPLIT_DELIMITER;
                        }//end of if (database1.getVolunteerList().get(volunteerIndex).canDoJob[jobIndex])
                    } // end of for (int jobIndex = 0; jobIndex < job.size(); jobIndex++)
                    volunteerList += jobsCanDo;
                } // end of for (int volunteerIndex = 0; volunteerIndex < database1.getVolunteerList().size(); volunteerIndex++)
                PrintWriter fileWriter = new PrintWriter(file);
                fileWriter.println(volunteerList);
                fileWriter.close();
            }
            catch (FileNotFoundException error)
            {
                lastAction = LastAction.FILE_NOT_FOUND;
                writeSuccessful = false;
            } // end of try
            if (writeSuccessful)
                lastAction = LastAction.SAVE_VOLUNTEER_LIST;
        } // end of if (file != null)
    } // end of method writeToFile()

    /*
     * Invokes the appropriate methods for when the user is adding and removing jobs. 
     */
    private void addAllJobs() throws IOException
    {
        validInputOption = new int[] {OPTION[0], OPTION [1], OPTION[2], OPTION[3] , OPTION[9], OPTION[10],OPTION[11]};
        showInstructions();
        showConsoleDisplayForAddingJobs();
        displayLastAction(lastAction);
        input = consoleReader.readLine();
        manageConsoleInput(input, validInputOption);
    } // end of addAllJobs() throws IOException

    /*
     * Invokes the appropriate methods for when the user is adding and removing volunteers.
     */
    private void addAllVolunteers() throws IOException
    {
        validInputOption = new int [] {OPTION[0], OPTION[4], OPTION[5], OPTION [6], OPTION[7], OPTION[9], OPTION[10]};
        showInstructions();
        showConsoleDisplayForAddingVolunteers();
        displayLastAction(lastAction);
        input = consoleReader.readLine();
        manageConsoleInput(input, validInputOption);
    } // end of addAllVolunteers() throws IOException
} // end of class Console