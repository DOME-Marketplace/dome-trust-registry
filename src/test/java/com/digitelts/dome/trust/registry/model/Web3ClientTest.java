package com.digitelts.dome.trust.registry.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.digitelts.dome.trust.registry.contracts.DIDList;

@ExtendWith(MockitoExtension.class)
public class Web3ClientTest {
    
    @Mock
    private DIDList contract;
    private String mockedId = "did:key:foo";
    private Web3Client w3;

    @BeforeEach
    void setup(){
        w3 = new Web3Client();
        ReflectionTestUtils.setField(w3, "contract", contract);
    }

    @Test
    void testSha256String_shouldReturnHash(){
        String mockedHash = "62b2e3d9ea9270a4e4c4faa1cc4218f7b2af9545cc15ed3bc4aa6f9ad8c278a4";

        String stringHash = assertDoesNotThrow(() -> {
            byte[] hash = Web3Client.sha256String(mockedId);
            return String.format("%064x", new BigInteger(1, hash));
        });

        assertEquals(mockedHash, stringHash);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testIncludeDID_shouldReturnHash() throws Exception {
        byte[] mockedHash = Web3Client.sha256String(mockedId);
        RemoteFunctionCall<TransactionReceipt> remoteCall = mock(RemoteFunctionCall.class);
        when(contract.includeDID(mockedHash)).thenReturn(remoteCall);
        when(remoteCall.send()).thenReturn(mock(TransactionReceipt.class));

        byte[] result = w3.includeDID(mockedId);

        assertArrayEquals(mockedHash, result);
        verify(contract).includeDID(mockedHash);
        verify(remoteCall).send();
    }

    @Test
    @SuppressWarnings("unchecked")
    void testRemoveDID_shouldReturnHash() throws Exception {
        byte[] expectedHash = Web3Client.sha256String(mockedId);
        RemoteFunctionCall<TransactionReceipt> remoteCall = mock(RemoteFunctionCall.class);
        when(contract.removeDID(expectedHash)).thenReturn(remoteCall);
        when(remoteCall.send()).thenReturn(mock(TransactionReceipt.class));

        w3.removeDID(mockedId);

        verify(contract).removeDID(expectedHash);
        verify(remoteCall).send();
    }
}
