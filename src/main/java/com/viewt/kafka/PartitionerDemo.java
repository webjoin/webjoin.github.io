/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viewt.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 *
 * @author Elijah
 */
public class PartitionerDemo implements Partitioner{
 
    public PartitionerDemo(VerifiableProperties props) {
 
    }
 
    @Override
    public int partition(Object obj, int numPartitions) {
        int partition = 0;
        if (obj instanceof String) {
            String key=(String)obj;
            int offset = key.lastIndexOf('.');
            if (offset > 0) {
                partition = Integer.parseInt(key.substring(offset + 1)) % numPartitions;
            }
        }else{
            partition = obj.toString().length() % numPartitions;
        }
         
        return partition;
    }
    
}
