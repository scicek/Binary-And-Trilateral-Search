package Logic;

/***************************************************************************************
 * Written by: Simon Cicek                                                             *
 * Last changed: 2012-03-27                                                            *
 ***************************************************************************************/

public class Searcher
{    
    // pr[0] represents the index of the searched element and pr[1] the amount of compares
    public static int[] pr = new int[2];
    
    private Searcher(){}
        
    public static int[] recursiveBinarySearch (int[] list, int element)
    {
        pr = new int[2];
        pr[0] = -1;
        pr[1] = 0;
        
        return recursiveBinarySearch(list,element,0,list.length-1);
    }
    
    public static int[] recursiveTrilateralSearch (int[] list, int element)
    {
        pr = new int[2];
        pr[0] = -1;
        pr[1] = 0;
        
        return recursiveTrilateralSearch(list,element,0,list.length-1);
    }
    
    public static int[] recursiveBinarySearch(int[] list, int element, int p_f, int p_l)
    {
        if (p_f <= p_l)
        {
            pr[1]++;
            int p_m = (p_f + p_l) / 2;
 
            // The element was found
            if(element == list[p_m])
            {                
                pr[1]++;
                pr[0] = p_m;
                return pr;
            }
            // Element < middlepoint
            else if (element < list[p_m])
            {                
                pr[1]++;
                return recursiveBinarySearch(list, element, p_f, p_m - 1);
            }
            // Element > middlepoint
            else if (element > list[p_m])
            {
                pr[1]++;
                return recursiveBinarySearch(list, element, p_m + 1, p_l);
            }
        }
        
        return pr;
    }
    
    public static int[] iterativeBinarySearch (int[] list, int element, int p_f, int p_l)
    {
        int[] p = new int[2];
        p[0] = -1;
        p[1] = 0;
        
        int p_m;
        
        while(p_f <= p_l)
        {
            p[1]++;
            p_m = (p_f + p_l)/2;

            // The Element was found
            if(element == list[p_m])
            {
                p[1]++;
                p[0] = p_m;
                return p;
            }
            // Element < middlepoint
            else if (element < list[p_m])
            {
                p[1]++;
                p_l = p_m - 1;
            }
            // Element > middlepoint
            else
            {
                p[1]++;
                p_f = p_m + 1;
            }
        }
        
        return p;
    }
    
    public static int[] recursiveTrilateralSearch (int[] list, int element, int p_f, int p_l)
    {
        // The two middlepoints
        int p_1m = p_f + (p_l - p_f)/3;
        int p_2m = p_f + (2 * (p_l - p_f))/3;
        
        if (p_f <= p_l)
        {
            // The element was found at the first middlepoint
            if(element == list[p_1m])
            {
                pr[1]++;
                pr[0] = p_1m;
                return pr;
            }
            // The element was found at the second middlepoint
            else if (element == list[p_2m])
            {            
                pr[1]++;
                pr[0] = p_2m;
                return pr;
            }
            
            // Element < first middlepoint
            if (element < list[p_1m]) 
            {
                pr[1]++;
                return recursiveTrilateralSearch(list, element, p_f, p_1m - 1);
            }
            // Element < second middlepoint
            else if (element < list[p_2m]) 
            {
                pr[1]++;
                return recursiveTrilateralSearch(list, element, p_1m + 1, p_2m - 1);
            }
            // Element > second middlepoint
            else
            {
                pr[1]++;
                return recursiveTrilateralSearch(list, element, p_2m + 1, p_l);
            }
        }
        
        return pr;
    }

    public static int[] iterativeTrilateralSearch (int[] list, int element, int p_f, int p_l)
    {
        int[] p = new int[2];
        p[0] = -1;
        p[1] = 0;
        
        int p_1m;
        int p_2m;
        
        while(p_f <= p_l)
        {
            p[1]++;
            // The two middlepoints
            p_1m = p_f + (p_l - p_f)/3;
            p_2m = p_f + (2 * (p_l - p_f))/3;
            
            // The element was found at the first middlepoint
            if(element == list[p_1m])
            {
                p[1]++;
                p[0] = p_1m;
                return p;
            }
            // The element was found at the second middlepoint
            else if(element == list[p_2m])
            {
                p[1]++;
                p[0] = p_2m;
                return p;
            }
            
            // Element < first middlepoint
            if (element < list[p_1m]) 
            {
                p[1]++;
                p_l = p_1m - 1;
            }
            // Element < second middlepoint
            else if (element < list[p_2m]) 
            {
                p[1]++;
                p_f = p_1m + 1;
                p_l = p_2m - 1;
            }
            // Element > second middlepoint
            else 
            {
                p[1]++;
                p_f = p_2m + 1;
            }
        }   
        
        return p;
    }
}