package kademlia;

import java.text.DecimalFormat;

/**
 * Class that keeps statistics for this Kademlia instance.
 *
 * These statistics are temporary and will be lost when Kad is shut down.
 *
 * @author Joshua Kissoon
 * @since 20140505
 */
public class Statistician
{

    /* How much data was sent and received by the server over the network */
    private long totalDataSent, totalDataReceived;
    private long numDataSent, numDataReceived;

    /* Bootstrap timings */
    private long bootstrapTime;

    /* Content lookup operation timing & route length */
    private int numContentLookups;
    private long totalContentLookupTime;
    private long totalRouteLength;

    
    {
        this.totalDataSent = 0;
        this.totalDataReceived = 0;
        this.bootstrapTime = 0;
        this.numContentLookups = 0;
        this.totalContentLookupTime = 0;
        this.totalRouteLength = 0;
    }

    /**
     * Used to indicate some data is sent
     *
     * @param size The size of the data sent
     */
    public void sentData(long size)
    {
        this.totalDataSent += size;
        this.numDataSent++;
    }

    /**
     * @return The total data sent
     */
    public long getTotalDataSent()
    {
        return this.totalDataSent;
    }

    /**
     * Used to indicate some data was received
     *
     * @param size The size of the data received
     */
    public void receivedData(long size)
    {
        this.totalDataReceived += size;
        this.numDataReceived++;
    }

    /**
     * @return The total data received
     */
    public long getTotalDataReceived()
    {
        return this.totalDataReceived;
    }

    /**
     * Sets the bootstrap time for this Kademlia Node
     *
     * @param time The bootstrap time in nanoseconds
     */
    public void setBootstrapTime(long time)
    {
        this.bootstrapTime = time;
    }

    public long getBootstrapTime()
    {
        return this.bootstrapTime;
    }

    /**
     * Add the timing for a new content lookup operation that took place
     *
     * @param time        The time the content lookup took in nanoseconds
     * @param routeLength The length of the route it took to get the content
     */
    public void addContentLookup(long time, int routeLength)
    {
        this.numContentLookups++;
        this.totalContentLookupTime += time;
        this.totalRouteLength += routeLength;
    }

    public int numContentLookups()
    {
        return this.numContentLookups;
    }

    public long totalContentLookupTime()
    {
        return this.totalContentLookupTime;
    }

    /**
     * Compute the average time a content lookup took
     *
     * @return The average time in milliseconds
     */
    public double averageContentLookupTime()
    {
        double avg = (double) ((double) this.totalContentLookupTime / (double) this.numContentLookups) / 1000000D;
        DecimalFormat df = new DecimalFormat("#.00");
        return new Double(df.format(avg));
    }

    public double averageContentLookupRouteLength()
    {
        double avg = (double) ((double) this.totalRouteLength / (double) this.numContentLookups);
        DecimalFormat df = new DecimalFormat("#.00");
        return new Double(df.format(avg));
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Statistician: [");
        
        sb.append("Bootstrap Time: ");
        sb.append(this.getBootstrapTime());
        sb.append("; ");
        
        sb.append("Data Sent: ");
        sb.append("(");
        sb.append(this.numDataSent);
        sb.append(") ");
        sb.append(this.getTotalDataSent());
        sb.append(" bytes; ");
        
        sb.append("Data Received: ");
        sb.append("(");
        sb.append(this.numDataReceived);
        sb.append(") ");
        sb.append(this.getTotalDataReceived());
        sb.append(" bytes; ");
        
        sb.append("Num Content Lookups: ");
        sb.append(this.numContentLookups());
        sb.append("; ");
        
        sb.append("Avg Content Lookup Time: ");
        sb.append(this.averageContentLookupTime());
        sb.append("; ");
        
        sb.append("Avg Content Lookup Route Lth: ");
        sb.append(this.averageContentLookupRouteLength());
        sb.append("; ");
        
        sb.append("]");
        
        return sb.toString();
    }
}
