package com.github.densi27.hellojava;

import java.text.SimpleDateFormat;
import java.time.*;

public class helloJava {

    /**
     * Class to generate timestamps with microsecond precision
     * For example: MicroTimestamp.INSTANCE.get() = "2012-10-21 19:13:45.267128"
     */
    public enum MicroTimestamp
    {  INSTANCE ;

        private long              startDate ;
        private long              startNanoseconds ;
        private SimpleDateFormat dateFormat ;

        private MicroTimestamp()
        {  this.startDate = System.currentTimeMillis() ;
            this.startNanoseconds = System.nanoTime() ;
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS") ;
        }

        public String get()
        {  long microSeconds = (System.nanoTime() - this.startNanoseconds) / 1000 ;
            long date = this.startDate + (microSeconds/1000) ;
            return this.dateFormat.format(date) + String.format("%03d", microSeconds % 1000) ;
        }
    }

    public static void main(String[] args){

        String s = MicroTimestamp.INSTANCE.get();
        LocalDateTime ldt = LocalDateTime.parse(s);
        System.out.println(ldt);

    }
}


