package com.digitelts.dome.trust.registry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Class for defining custom properties to be used in the application.yml file
 */
@Component
@ConfigurationProperties
public class CustomProperties {
    @ConfigurationProperties(prefix = "eth")
    public class Web3Properties {
        private String provider;
        private String address;
        private String key;
        private ContractProperties contract;
        public class ContractProperties {
            private String address;
            public String getAddress() {
                return address;
            }
            public void setAddress(String address) {
                this.address = address;
            }
        }
        public String getProvider() {
            return provider;
        }
        public void setProvider(String provider) {
            this.provider = provider;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public ContractProperties getContract() {
            return contract;
        }
        public void setContract(ContractProperties contract) {
            this.contract = contract;
        }
    }
}
