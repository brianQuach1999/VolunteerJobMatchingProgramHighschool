import java.lang.Integer;
import java.util.LinkedList;

/**
 * A database that assigns jobs to volunteers to minimize the number of volunteers without jobs.
 * 
 * @author 
 * @version (1.0.1)
 */
public class Database
{
    // instance variables
    private LinkedList <Job> job; 
    private boolean jobsHaveBeenAssigned;
    private LinkedList <Volunteer> volunteer;

    /**
     * Constructs an empty database.
     */
    public Database()
    {
        job = new LinkedList();
        jobsHaveBeenAssigned = false;
        volunteer = new LinkedList();
    } // end of constructor Database()

    /**
     * Returns the job list of this database.
     * 
     * @return the job list of this database
     */
    public LinkedList <Job> getJobList()
    {
        return job;
    } // end of accessor getJobList()

    /**
     * Returns if jobs have been assigned to volunteers yet.
     *
     * @return if jobs have been assigned to volunteers yet
     */
    public boolean getJobsHaveBeenAssigned()
    {
        return jobsHaveBeenAssigned;
    } // end of accessorgetJobsHaveBeenAssigned() 

    /**
     * Returns the volunteer list of this database.
     * 
     * @return the volunteer list of this database.
     */
    public LinkedList <Volunteer> getVolunteerList()
    {
        return volunteer;
    } // end of accessor getVolunteerList()

    /**
     * Sets the job list of this database.
     * 
     * @param job the job list of this database <br>
     * <i>precondition:</i> <code>job</code> cannot be null
     */
    public void setJobList(LinkedList <Job> job)
    {
        if (job != null)
        {
            this.job = job;
        } // end of if (job != null)
    } // end of mutator setJobList(Job job)

    /**
     * Sets the job list of this database.
     * 
     * @param jobsHaveBeenAssigned if jobs have been assigned to volunteers yet
     */
    public void setJobsHaveBeenAssigned(boolean jobsHaveBeenAssigned)
    {
        this.jobsHaveBeenAssigned = jobsHaveBeenAssigned;
    } // end of mutator setJobsHaveBeenAssigned(boolean jobsHaveBeenAssigned)

    /**
     * Sets the volunteer list of this database.
     * 
     * @param volunteer the volunteer list of this database <br>
     * <i>precondition:</i> <code>volunteer</code> cannot be null
     */
    public void setVolunteerList(LinkedList <Volunteer> volunteer)
    {
        if (volunteer != null)
        {
            this.volunteer = volunteer;
        } // end of if (volunteer != null)
    } // end of mutator setVolunteerList(LinkedList volunteer)

    /**
     * Checks if the proposed job already exists, and if not, creates the given job with the given number of volutneers required and adds it to the end of the list of jobs. 
     * 
     * @param jobTitle the job title of this job to be added <br>
     * <i>precondition:</i> <code>jobTitle</code> cannot be null
     * @param numberOfVolunteersRequired the number of volunteers required of this job
     * @return true if the job was successfully created and added; false otherwise
     */
    public boolean addJob(String jobTitle, int numberOfVolunteersRequired)
    {
        if (jobTitle != null && numberOfVolunteersRequired > 0 )
        {
            boolean jobAlreadyExists = false;
            for (int jobIndex = 0; jobIndex < job.size(); jobIndex++)
            {
                if (job.get(jobIndex).getJobTitle().equals(jobTitle))
                {
                    jobAlreadyExists = true;
                } // end of if (job.get(jobIndex).getTitle().equals(jobTitle)
            } // end of for (int jobIndex = 0; jobIndex < job.size(); jobIndex++)
            if (!jobAlreadyExists)
            {
                job.add(new Job(jobTitle, numberOfVolunteersRequired));
                return true;
            } // end of if (!jobAlreadyExists)
        } // end of if (jobTitle != null && numberOfVolunteersRequired > 0 )
        return false;
    } // end of addJob(String jobTitle, int numberOfVolunteersRequired) throws IOException

    /**
     * Checks if the volunteer already exists, and if not, creates a volunteer with the given family name and given name and adds it to the end of the list of volunteers.
     * 
     * @param familyName the family name of this volunteer to be added <br>
     * <i>precondition:</i> <code>familyName</code> cannot be null
     * @param givenName the given name of this volunteer to be added <br>
     * <i>precondition:</i> <code>givenName</code> cannot be null
     * @return true if the volunteer was successfully created and added; false otherwise
     */
    public boolean addVolunteer(String familyName, String givenName)
    {
        if (familyName != null && givenName != null)
        {
            boolean volunteerAlreadyExists = false;
            for (int volunteerIndex = 0; volunteerIndex < volunteer.size(); volunteerIndex++)
            {
                if (volunteer.get(volunteerIndex).getFamilyName().equals(familyName) && volunteer.get(volunteerIndex).getGivenName().equals(givenName))
                {
                    volunteerAlreadyExists = true;
                } // end of if (volunteer.get(volunteerIndex).getFamilyName().equals(familyName) && volunteer.get(volunteerIndex).getGivenName().equals(givenName))
            } // end of for (int volunteerIndex = 0; volunteerIndex < volunteer.size(); volunteerIndex++)
            if (!volunteerAlreadyExists)
            {
                volunteer.add(new Volunteer(familyName, givenName, job,job.size()));
                return true;
            } // end of if (!volunteerAlreadyExists)
        } // end of if (familyName != null && givenName != null)
        return false;
    } // end of addVolunteer(String givenName, String familyName) throws IOException

    /**
     * Checks if the volunteer already exists, and if not, creates a volunteer with the given family name, given name, and jobs it can do, and adds it to the end of the list of volunteers.
     *
     *@param familyName the family name of this volunteer to be added <br>
     * <i>precondition:</i> <code>familyName</code> cannot be null
     * @param givenName the given name of this volunteer to be added <br>
     * <i>precondition:</i> <code>givenName</code> cannot be null
     * @param canDoJob the boolean array representing the jobs the volunteer can and cannot do; true if they can do the job at the index; false otherwise <br>
     * <i>precondition:</i> <code>canDoJob</code> cannot be null
     * @return true if the volunteer was successfully created and added; false otherwise
     */
    public boolean addVolunteer(String familyName, String givenName, boolean[] canDoJob)
    {
        if (familyName != null && givenName != null)
        {
            boolean volunteerAlreadyExists = false;
            for (int volunteerIndex = 0; volunteerIndex < volunteer.size(); volunteerIndex++)
            {
                if (volunteer.get(volunteerIndex).getFamilyName().equals(familyName) && volunteer.get(volunteerIndex).getGivenName().equals(givenName))
                {
                    volunteerAlreadyExists = true;
                } // end of if (volunteer.get(volunteerIndex).getFamilyName().equals(familyName) && volunteer.get(volunteerIndex).getGivenName().equals(givenName))
            } // end of for (int volunteerIndex = 0; volunteerIndex < volunteer.size(); volunteerIndex++)
            if (!volunteerAlreadyExists)
            {
                volunteer.add(new Volunteer(familyName, givenName, canDoJob, job));
                return true;
            } // end of if (!volunteerAlreadyExists)
        } // end of if (familyName != null && givenName != null)
        return false;
    }

    /**
     * Assigns the jobs to the vounteers to minimize the number of volunteers without jobs.
     */
    public void assignJobsToVolunteers()
    {
        final int INVALID_JOB_INDEX = -1;
        volunteer = bubbleSortVolunteerList(volunteer);
        for (int volunteerIndex = 0; volunteerIndex < volunteer.size(); volunteerIndex++)
        {
            double lowestVolunteerToVolunteersNeededPercentage = Integer.MAX_VALUE;
            int jobIndexOfLowestVolunteerToVolunteersNeededPercentage = INVALID_JOB_INDEX;
            for(int jobIndex = 0; jobIndex < job.size(); jobIndex++)
            {
                if(volunteer.get(volunteerIndex).getCanDoJob()[jobIndex] && getJobVolunteersToVolunteersNeededPercentage(jobIndex) < lowestVolunteerToVolunteersNeededPercentage)
                {
                    lowestVolunteerToVolunteersNeededPercentage = getJobVolunteersToVolunteersNeededPercentage(jobIndex);
                    jobIndexOfLowestVolunteerToVolunteersNeededPercentage = jobIndex;
                } // end of if(volunteer.get(volunteerIndex).getCanDoJob()[jobIndex] && getJobVolunteersToVolunteersNeededPercentage(jobIndex) < lowestVolunteerToVolunteersNeededPercentage)
            } // end of for(int jobIndex = 0; jobIndex < job.size(); jobIndex++)

            if (jobIndexOfLowestVolunteerToVolunteersNeededPercentage != INVALID_JOB_INDEX && job.get(jobIndexOfLowestVolunteerToVolunteersNeededPercentage).getNumberOfVolunteersNeeded() != 0)
            {
                volunteer.get(volunteerIndex).setActualJob(job.get(jobIndexOfLowestVolunteerToVolunteersNeededPercentage));
                job.get(jobIndexOfLowestVolunteerToVolunteersNeededPercentage).setNumberOfVolunteersNeeded
                (job.get(jobIndexOfLowestVolunteerToVolunteersNeededPercentage).getNumberOfVolunteersNeeded() - 1);
            }
            else
            {
                volunteer.get(volunteerIndex).getActualJob().setJobTitle("no job needs to be trained");
            } // end of if (jobIndexOfLowestVolunteerToVolunteersNeededPercentage != INVALID_JOB_INDEX && lowestVolunteerToVolunteersNeededPercentage < maximumCapacityPercentage)

        } // end of for (int numberOfJobsCanDo = 1; numberOfJobsCanDo < job.size(); numberOfJobsCanDo++)
        jobsHaveBeenAssigned = true;
    } // end of assignJobsToVolunteers()

    /**
     * Bubble sorts the given job list in order from least number of volunteers neeeded, to greatest.
     * 
     * @param jobList<br>
     * <i>precondition:</i> <code>jobList</code> cannot be null
     */
    public LinkedList bubbleSortJobList(LinkedList <Job> jobList)
    {
        int j = 0;
        boolean swapWasMade = true;
        Job temporaryJobHolder;
        while (swapWasMade)
        {
            j++;
            swapWasMade = false;
            for (int i = 0; i < jobList.size()-1; i++)
            {
                if (jobList.get(i).getNumberOfVolunteersNeeded() > jobList.get(i+1).getNumberOfVolunteersNeeded())
                {
                    temporaryJobHolder = jobList.get(i);
                    jobList.set(i, jobList.get(i+1));
                    jobList.set(i+1, temporaryJobHolder);
                    swapWasMade = true;
                } // end of if (jobList.get(i).getNumberOfVolunteersNeeded() > jobList.get(i+1).getNumberOfVolunteersNeeded())
            } // end of for (int i = 0; i < jobList.size()-1; i++)
        } // end of while
        return jobList;
    } // end of bubbleSortJobList(LinkedList <Job> jobList)
    
    /**
     * Bubble sorts the given volunteer list in order from least number of jobs the volunteer is able to do, to greatest.
     * 
     * @param volunteerList <br>
     * <i>precondition:</i> <code>volunteerList</code> cannot be null
     */
    public LinkedList bubbleSortVolunteerList(LinkedList <Volunteer> volunteerList)
    {
        if (volunteerList != null)
        {
            int j = 0;
            boolean swapWasMade = true;
            Volunteer temporaryVolunteerHolder;
            while (swapWasMade)
            {
                j++;
                swapWasMade = false;
                for (int i = 0; i < volunteerList.size()-1; i++)
                {
                    if (volunteerList.get(i).getNumberOfJobsCanDo() > volunteerList.get(i+1).getNumberOfJobsCanDo())
                    {
                        temporaryVolunteerHolder = volunteerList.get(i);
                        volunteerList.set(i, volunteerList.get(i+1));
                        volunteerList.set(i+1, temporaryVolunteerHolder);
                        swapWasMade = true;
                    } // end of if (volunteerList.get(i).getNumberOfJobsCanDo() > volunteerList.get(i+1).getNumberOfJobsCanDo())
                } // end of for (int i = 0; i < volunteerList.size(); i++)
            } // end of while
            return volunteerList;
        } // end of if (volunteerList != null)
        return null;
    } // end of LinkedList bubbleSort(LinkedList <Volunteer> volunteerList)

    /**
     * Deletes the job at the given index in the job list.
     * 
     * @param jobIndex the index in the job list of the job to be deleted
     * @return true if the job was successfully deleted; false otherwise
     */
    public boolean deleteJob(int jobIndex)
    {
        if (jobIndex >= 0 && jobIndex < job.size())
        {
            job.remove(jobIndex);
            return true;
        } // end of if (jobIndex >= 0 && jobIndex < job.size())
        return false;
    } // end of deleteJob(int jobIndex) throws IOException

    /**
     * Deletes the volunteer at the given index in the volunteer list.
     * 
     * @param volunteerIndex the index in the volunteer list of the volunteer to be deleted
     * @return true if the volunteer was successfully created; false otherwise
     */
    public boolean deleteVolunteer(int volunteerIndex)
    {
        if (volunteerIndex >= 0 && volunteerIndex < volunteer.size())
        {
            volunteer.remove(volunteerIndex);
            return true;
        } // end of if (volunteerIndex >= 0 && volunteerIndex < volunteer.size())
        return false;
    } // end of deleteVolunteer(int volunteerIndex)

    /*
     * Calculates and returns the volunteer to volunteers needed precentage of the job at the given index.
     */
    private double getJobVolunteersToVolunteersNeededPercentage(int jobIndex)
    {
        int numberOfPossibleJobWorkers = 0;
        for (int volunteerIndex = 0; volunteerIndex < volunteer.size(); volunteerIndex++)
        {
            if (volunteer.get(volunteerIndex).getCanDoJob()[jobIndex])
            {
                numberOfPossibleJobWorkers += 1;
            } // end of if (volunteer.get(volunteerIndex).getCanDoJob()[jobIndex])
        } // end of for (int volunteerIndex = 0; volunteerIndex < volunteer.size(); volunteerIndex++)
        return (double) numberOfPossibleJobWorkers/ (double) job.get(jobIndex).getNumberOfVolunteersNeeded();
    } // end of getJobVolunteersToVolunteersNeededPercentage(int jobIndex)

} // end of class Database
