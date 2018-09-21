
/**
 * A job that knows its title and number of volunteers it needs.
 * 
 * @author 
 * @version (1.0.1)
 */
public class Job
{
    // instance variables
    private String jobTitle;
    private int numberOfVolunteersNeeded;

    /**
     * Constructor a job with default characteristics.
     */
    public Job()
    {
        jobTitle = "";
        numberOfVolunteersNeeded = 0;
    } // end of constructor Job()

    /**
     * Constructors a job with the specified job title and number of volunteers needed.
     * 
     * @param jobTitle the job title of this job <br>
     * <i>precondition:</i> <code>jobTitle</code> cannot be null
     * @param numberOfVolunteersNeeded the number of volunteer this job needs
     */
    public Job(String jobTitle, int numberOfVolunteersNeeded)
    {
        if (jobTitle != null)
        {
            this.jobTitle = jobTitle;
            this.numberOfVolunteersNeeded = numberOfVolunteersNeeded;
        } // end of if (jobTitle != null)
    }// end of constructor Job(String jobTitle, int numberOfVolunteersNeeded)

    /**
     * Returns the job title of this job.
     * 
     * @return the job title of this job
     */
    public String getJobTitle()
    {
        return jobTitle;
    } // end of accessor getJobTitle()

    /**
     * Returns the number of volunteers this job needs.
     * 
     * @return the numberof volunteers this job needs
     */
    public int getNumberOfVolunteersNeeded()
    {
        return numberOfVolunteersNeeded;
    } // end of accessor getNumberOfVolunteersNeeded()

    /**
     * Sets the job title of this job.
     * 
     * @param jobTitle the job title of this job <br>
     * <i>precondition:</i> <code>jobTitle</code> cannot be null
     * 
     */
    public void setJobTitle(String jobTitle)
    {
        if (jobTitle != null)
        {
            this.jobTitle = jobTitle;
        } // end of if (jobTitle != null)
    } // end of mutator setJobTitle(String jobTitle)

    /**
     * Sets the number of volunteers this job needs.
     * 
     * @param numberOfVolunteersNeeded the number of volunteers this job needs <br>
     * <i>precondition:</i> <code>numberOfVolunteersNeeded</code> must be 0 or greater
     */
    public void setNumberOfVolunteersNeeded(int numberOfVolunteersNeeded)
    {
        if (numberOfVolunteersNeeded >= 0)
        {
            this.numberOfVolunteersNeeded = numberOfVolunteersNeeded;
        } // end of this.numberOfVolunteersNeeded = numberOfVolunteersNeeded;
    } // end of mutator setNumberOfVolunteersNeeded(int numberOfVolunteersNeeded)

    /**
     * Returns a string representation of this job.
     * 
     * @return a string representation of this job
     */
    public String toString()
    {
        return 
        getClass().getName()
        + "["
        + "Job Title: " + jobTitle
        + ", # of volunteers needed: " + numberOfVolunteersNeeded
        + "]";
    } // end of method toString()
} // end of class Job
