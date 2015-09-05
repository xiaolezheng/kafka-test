package com.weibangong.msg;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by xiaolezheng on 15/8/26.
 */
public class TenantIdPartitioner implements Partitioner {
        public TenantIdPartitioner(VerifiableProperties verifiableProperties) {

        }

        public int partition(Object key, int numPartitions) {
            try {

                return Math.abs(Integer.parseInt((String) key) % numPartitions);
            } catch (Exception e) {
                return Math.abs(key.hashCode() % numPartitions);
            }
        }
    }

