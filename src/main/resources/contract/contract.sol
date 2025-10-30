// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract DIDList{

    mapping (string => bool) public didlist;

    function isDIDIncluded(string calldata did) external view returns(bool){
        return(didlist[did]);
    }

    function includeDID(string calldata did) external {
        didlist[did] = true;
    }

    
    function removeDID(string calldata did) external {
        didlist[did] = false;
    }

}