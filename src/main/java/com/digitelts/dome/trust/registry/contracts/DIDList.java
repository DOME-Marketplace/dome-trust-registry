package com.digitelts.dome.trust.registry.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * Class represenging the DIDList smart-contract for saving Trust Participant DIDs in the Blockchain network
 * 
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class DIDList extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_DIDLIST = "didlist";

    public static final String FUNC_INCLUDEDID = "includeDID";

    public static final String FUNC_ISDIDINCLUDED = "isDIDIncluded";

    public static final String FUNC_REMOVEDID = "removeDID";

    @Deprecated
    protected DIDList(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DIDList(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DIDList(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DIDList(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> didlist(String param0) {
        final Function function = new Function(FUNC_DIDLIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> includeDID(String did) {
        final Function function = new Function(
                FUNC_INCLUDEDID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(did)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isDIDIncluded(String did) {
        final Function function = new Function(FUNC_ISDIDINCLUDED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(did)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeDID(String did) {
        final Function function = new Function(
                FUNC_REMOVEDID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(did)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DIDList load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DIDList(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DIDList load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DIDList(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DIDList load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DIDList(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DIDList load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DIDList(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
