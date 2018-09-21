import java.util.LinkedList;
/**
 * A volunteer that has a given name and a family name. It knows all the jobs, what jobs it can do, the number of jobs it can do, and its actual job.
 * 
 * @author 
 * @version (1.0.1)
 */
public class Volunteer
{
    //  instance variables
    private Job actualJob;
    private boolean[] canDoJob;
    private String familyName;
    private String givenName;
    private LinkedList<Job> job;
    private int numberOfJobsCanDo;

    /**
     * Constructs a volunteer with default characteristics.
     */
    public Volunteer()
    {
        actualJob = new Job();
        canDoJob = new boolean[0];
        familyName = "";
        givenName = "";
        job = new LinkedList();
        numberOfJobsCanDo = 0;
    } // end of constructor Volunteer()

    /**
     * Constructors a volunteer with the specificed family name, given name, list of all the jobs, and number of jobs it can do.
     * 
     * @param familyName the family name <br>
     * <i>precondition:</i> <code>familyName</code> cannot be null
     * @param givenName the given name <br>
     * <i>precondition:</i> <code>givenName</code> cannot be null
     * @param job the list of all the jobs <br>
     * <i>precondition:</i> <code>job</code> cannot be null
     * @param the number of jobs this volunteer can do
     */
    public Volunteer(String familyName, String givenName, LinkedList job,int numberOfJobs)
    {
        if (familyName != null && givenName != null)
        {
            actualJob = new Job();
            canDoJob = new boolean[numberOfJobs];
            this.familyName = familyName;
            this.givenName = givenName;
            this.job = job;
            numberOfJobsCanDo = 0;
        } // end of if (familyName != null && givenName != null)
    } // end of constructor Volunteer(String familyName, String givenName, ArrayList job,int numberOfJobs)

    /**
     * Constructurs a volunteer with the specified family name, given name, list of jobs it can do, and list of all the jobs.
     * 
     * @param familyName the family name <br>
     * <i>precondition:</i> <code>familyName</code> cannot be null
     * @param givenName the given name <br>
     * <i>precondition:</i> <code>givenName</code> cannot be null
     * @param canDoJob the jobs this volunteer can do <br>
     * <i><i>precondition:</i> <code>canDoJob</code> cannot be null
     * @param job the list of all the jobs <br>
     * <i>precondition:</i> <code>job</code> cannot be null
     */
    public Volunteer(String familyName, String givenName, boolean[] canDoJob, LinkedList job)
    {
        actualJob = new Job();
        this.canDoJob = canDoJob;
        this.familyName = familyName;
        this.givenName = givenName;
        this.job = job;
        numberOfJobsCanDo = 0;
        for (int jobIndex = 0; jobIndex < canDoJob.length; jobIndex++)
        {
            if (canDoJob[jobIndex])
            {
                numberOfJobsCanDo += 1;
            }// end of if (canDoJob[jobIndex])
        }// end of for (int jobIndex = 0; jobIndex < canDoJob.length; jobIndex++)
    } // end of constructor Volunteer(String familyName, String givenName, boolean[] canDoJob)

    /**
     * Returns the actual job of this volutunteer.
     * 
     * @return the actual job of this volunteer
     */
    public Job getActualJob()
    {
        return actualJob;
    } // end of accessor getActualJob()

    /**
     * Returns a boolean array with the indexes of the jobs this volunteer can do being true; false otherwise.
     * 
     * @return a boolean array with the indexes of the jobs this volunteer can do being true; false otherwise
     */
    public boolean[] getCanDoJob()
    {
        return canDoJob;
    } // end of accessor getCanDoJob()

    /**
     * Returns the family name of this volunteer.
     * 
     * @return the family name of this volunteer
     */
    public String getFamilyName()
    {
        return familyName;
    } // end of accessor getFamilyName()

    /**
     * Returns the given name of this volunteer.
     * 
     * @return the given name of this volunteer
     */
    public String getGivenName()
    {
        return givenName;
    } // end of accessor getGivenName()

    /**
     * Returns the job list of this volunteer.
     * 
     * @return the job list of this volunteer
     */
    public LinkedList <Job> getJobList()
    {
        return job;
    } // end of accessor getJobList()

    /**
     * Returns the given name, family name, and actual job title of this volunteer.
     * 
     * @return the given name, family name, and actual job title of this volunteer
     */
    public String getNameAndActualJobTitle()
    {
        return givenName + " " + familyName + " - " + actualJob.getJobTitle();
    } // end of accessor getNameAndActualJobTitle()

    /**
     * Returns the given name, family name, and potential job titles this volunteer can do.
     * 
     * @return the given name, family name, and potential job titles this volunteer can do
     */
    public String getNameAndPotentialJobTitles()
    {
        String jobsCanDo = "";
        for (int jobIndex = 0; jobIndex < job.size(); jobIndex++)
        {

            if (canDoJob[jobIndex])
            {
                jobsCanDo = jobsCanDo + job.get(jobIndex).getJobTitle() + ", ";
            } //end of if (canDoJob[jobIndex])
        } // end of for (int jobIndex = 0; jobIndex < job.size(); jobIndex++)
        return givenName + " " + familyName + " - " + jobsCanDo;
    } // end of accessor getNameAndPotentialJobTitles()

    /**
     * Returns the number of jobs this volunteer can do.
     * 
     * @return the number of jobs this volunteer can do
     */
    public int getNumberOfJobsCanDo()
    {
        return numberOfJobsCanDo;
    } // end of accessor getNumberOfJobsCanDo()

    /**
     * Sets the actual job of this volunteer.
     * 
     * @param actualJob the actual job of this volunteer <br>
     * <i>precondition:</i> <code>actualJob</code> cannot be null
     */
    public void setActualJob(Job actualJob)
    {
        if (actualJob != null)
        {
            this.actualJob = actualJob; 
        } // end of if (actualJob != null)
    } // end of mutator setActualJob(Job jobGiven)

    /**
     * Sets the ability of this volunteer to do the job at each index.
     * 
     * @param canDoJob the job ability list <br>
     * <i>precondition:</i> <code>canDoJob</code> cannot be null
     */
    public void setCanDoJob(boolean[] canDoJob)
    {
        if (canDoJob != null)
        {
            this.canDoJob = canDoJob; 
        }  // end of if (canDoJob != null)
    } // end of mutator setCanDoJob(boolean[] canDoJob)

    /**
     * Sets the family name of this volunteer.
     * 
     * @param familyName the family name of this volunteer <br>
     * <i>precondition:</i> <code>familyName</code> cannot be null
     */
    public void setFamilyName(String familyName)
    {
        if (familyName != null)
        {
            this.familyName = familyName; 
        }  // end of if (familyName != null)
    } // end of mutator setFamilyName(String familyName)

    /**
     * Sets the given name of this volunteer.
     * 
     * @param givenName the given name of this volunteer <br>
     * <i>precondition:</i> <code>givenName</code> cannot be null
     */
    public void setGivenName(String givenName)
    {
        if (givenName != null)
        {
            this.givenName = givenName; 
        } // end of if (givenName != null)
    } // end of mutator setGivenName(String givenName)

    /**
     * Sets the job list of this volunteer
     * 
     * @param job the job list of this volunteer <br>
     * <i>precondition:</i> <code>job</code> cannot be null
     * 
     */
    public void setJobList(LinkedList <Job> job)
    {
        if (job != null)
        {
            this.job = job; 
        } // end of if (job != null)
    } // end of mutator setJobList(ArrayList <Job> job)

    /**
     * Sets the number of jobs this volunteer can do.
     * 
     * @param numberOfJobsCanDo the number of jobs this volunteer can do <br>
     * <i>precondition:</i> <code>numberOfJobsCanDo</code> must be positive
     */
    public void setNumberOfJobsCanDo(int numberOfJobsCanDo)
    {
        if (numberOfJobsCanDo >= 0)
        {
            this.numberOfJobsCanDo = numberOfJobsCanDo; 
        } // end of if (numberOfJobsCanDo >= 0)
    } // end of mutator setNumberOfJobsCanDo(int numberOfJobsCanDo)

    /**
     * Updates the number of jobs this volunteer can do.
     */
    public void updateNumberOfJobsCanDo()
    {
        for (int jobIndex = 0; jobIndex < canDoJob.length; jobIndex++)
        {
            numberOfJobsCanDo = 0;
            if (canDoJob[jobIndex])
            {
                numberOfJobsCanDo += 1;
            }// end of if (canDoJob[jobIndex])
        }// end of for (int jobIndex = 0; jobIndex < canDoJob.length; jobIndex++)
    } // end of method updateNumberOfJobsCanDo()

    /**
     * Returns a string representation of this volunteer.
     * 
     * @return a string representation of this volunteer
     */
    public String toString()
    {
        String elements = 
            getClass().getName()
            + "["
            +"Given Name: " + givenName
            +", Family Name: " + familyName
            + ", Actual Job: " + actualJob.getJobTitle()
            + ", # of potential jobs: " + numberOfJobsCanDo
            + ", List of Jobs:";
        for (int jobIndex = 0; jobIndex< job.size(); jobIndex++)
        {   
            if (canDoJob[jobIndex])
            {
                elements += " "+ job.get(jobIndex);
            } // end of if (canDoJob[jobIndex])
        } // end of for (int jobIndex = 0; jobIndex< job.size(); jobIndex++)
        elements += "]" ;
        return elements;
    } // end of method toString()
} // end of class Volunteer
