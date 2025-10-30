package com.digitelts.dome.trust.registry.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
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
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class DIDList extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b506102ba8061001c5f395ff3fe608060405234801561000f575f5ffd5b506004361061004a575f3560e01c80633291d1421461004e578063376e010114610063578063733f3ef7146100765780638d01c1de1461009d575b5f5ffd5b61006161005c366004610140565b6100ca565b005b610061610071366004610140565b610101565b610089610084366004610140565b610113565b604051901515815260200160405180910390f35b6100896100ab3660046101c2565b80516020818301810180515f8252928201919093012091525460ff1681565b60015f83836040516100dd929190610275565b908152604051908190036020019020805491151560ff199092169190911790555050565b5f5f83836040516100dd929190610275565b5f5f8383604051610125929190610275565b9081526040519081900360200190205460ff16905092915050565b5f5f60208385031215610151575f5ffd5b823567ffffffffffffffff811115610167575f5ffd5b8301601f81018513610177575f5ffd5b803567ffffffffffffffff81111561018d575f5ffd5b85602082840101111561019e575f5ffd5b6020919091019590945092505050565b634e487b7160e01b5f52604160045260245ffd5b5f602082840312156101d2575f5ffd5b813567ffffffffffffffff8111156101e8575f5ffd5b8201601f810184136101f8575f5ffd5b803567ffffffffffffffff811115610212576102126101ae565b604051601f8201601f19908116603f0116810167ffffffffffffffff81118282101715610241576102416101ae565b604052818152828201602001861015610258575f5ffd5b816020840160208301375f91810160200191909152949350505050565b818382375f910190815291905056fea26469706673582212201daddc58f8a2b7a897a2c071fa02684f8f5f2599083f2f98d053af3ee02cbdcb64736f6c634300081e0033";

    private static String librariesLinkedBinary;

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

    public static RemoteCall<DIDList> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DIDList.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DIDList> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DIDList.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DIDList> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DIDList.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DIDList> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DIDList.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    //public static void linkLibraries(List<Contract.LinkReference> references) {
    //    librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    //}

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
