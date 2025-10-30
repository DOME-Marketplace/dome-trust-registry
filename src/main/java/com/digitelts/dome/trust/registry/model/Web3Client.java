package com.digitelts.dome.trust.registry.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import com.digitelts.dome.trust.registry.contracts.DIDList;


/**
 * Class for Web3 operations
 */
@Component
public class Web3Client {

    /**
     * RPC node address
     */
    @Value("${eth.provider}")
    private String provider;
    /**
     * Account address
     */
    @Value("${eth.address}")
    private String address;
    /**
     * Account private key
     */
    @Value("${eth.key}")
    private String key;
    /**
     * Contract address
     */
    @Value("${eth.contract.address}")
    private String contractAddress;

    /**
     * Web3j handler
     */
    private Web3j w3;
    /**
     * Network credentials
     */
    private Credentials credentials;
    /**
     * Contract
     */
    private DIDList contract;

    /**
     * Method for initializing the Web3 client
     */
    @PostConstruct
    public void init(){
        try{
            System.out.println("\nInitializing Web3Client...");
            System.out.println("Provider: " + this.provider);
            System.out.println("Address: " + this.address);
            System.out.println("Key: " + this.key);
            System.out.println("Contract Address: " + this.contractAddress);
            web3Connect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\n\tERROR:"+e.getMessage());
            throw e;
        }
    }

    /**
     * Method for connecting to the Blockchain network
     */
    private void web3Connect(){
        this.w3 = Web3j.build(new HttpService(this.provider));
        this.credentials = Credentials.create(this.key);
        this.contract = DIDList.load(contractAddress, w3, credentials, new DefaultGasProvider());
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

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    /**
     * Method for dumping a Trust Participant DID into the Blockchain
     * @param did The Trust Participant DID to be dumped
     */
    public void includeDID(String did) throws Exception{
        contract.includeDID(did).send();
    }

    /**
     * Method for disabling a Trust Participant DID in the Blockchain
     * @param did The Trust Participant DID to be disabled
     */
    public void removeDID(String did) throws Exception{
        contract.removeDID(did).send();
    }
}
