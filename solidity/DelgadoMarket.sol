
// SPDX-License-Identifier: Unlicensed
pragma solidity >=0.7.0 <0.9.0;

/**
 * @title DelgadoMarket - Deal sheet market place
 * @author Tony Kunz
 * @dev this has been built to sell protocol dealsheets on the blockchain
 */
contract DelgadoMarket { 
    
    address admin; 
    
    struct DealSheet {
        uint256 _sheetId; 
        string  _protocol; 
        uint256 _issueDate; 
        uint256 _expireDate; 
        uint256 _maxYield; 
        uint256 _minYield; 
        uint256 _maxSupply; 
        uint256 _minSupply; 
        uint256 _maxBorrow; 
        uint256 _minBorrow; 
        uint256 _dealCount;

    }
    
    struct DealSheetPurchase{
        uint256 _sheetId; 
        string  _protocol; 
        uint256 _dealCount; 
        uint256 _purchaseDate; 
        string  _cid; 
    }
    
    mapping(address=>DealSheetPurchase[]) dealsheetPurchasesByAddress; 
    
    mapping(string=>DealSheet[]) dealsheetsByProtocol;
    mapping(uint256=>DealSheet) dealSheetById; 
    
    mapping(uint256=>DealSheet[]) dealSheetsByDate;
    
    mapping(uint256=>string) cidBySheetId; 
    
    uint256 [] dates; 
    
    
    constructor (address _admin) {
        admin = _admin; 
    }
    
    
    function findProtocolDealSheets(string memory _protocolName) external view returns (DealSheet [] memory _dealSheets){
        return dealsheetsByProtocol[_protocolName];
    }
    
    
    function getAvailableDealSheets() external view returns (  DealSheet [] memory _dealSheets) {
        return dealSheetsByDate[getLatestDate()];
    }
    
    
    function getDealSheetLatestCount() external view returns(uint256 _x){
        return dealSheetsByDate[getLatestDate()].length; 
    }
           

    function viewPurchasedDealSheets() external view returns (DealSheetPurchase [] memory _purchases) {
         return dealsheetPurchasesByAddress[msg.sender];
    }
           
           
    function buyDealSheet(uint256 _sheetId, uint256 _fee) external  payable returns (uint256 _purchaseDate, string memory _cid) {
        require(msg.value == _fee, 'invalid purchase amount');
        DealSheet memory dealSheet = dealSheetById[_sheetId];
        DealSheetPurchase memory purchase = DealSheetPurchase({
                      _sheetId : _sheetId, 
                      _protocol : dealSheet._protocol, 
                      _dealCount : dealSheet._dealCount, 
                      _purchaseDate : block.timestamp, 
                      _cid : cidBySheetId[_sheetId]
        });
        dealsheetPurchasesByAddress[msg.sender].push(purchase);
        return (purchase._purchaseDate, cidBySheetId[_sheetId]);
    }
    


    
    
    function uploadNewDealSheets(uint256 [] memory _sheetId, 
                                string [] memory _protocol, 
                                uint256 [] memory _issueDate, 
                                uint256 [] memory _expireDate, 
                                uint256 [] memory _maxYield, 
                                uint256 [] memory _minYield, 
                                uint256 [] memory _maxSupply, 
                                uint256 [] memory _minSupply, 
                                uint256 [] memory _maxBorrow, 
                                uint256 [] memory _minBorrow, 
                                uint256 [] memory _dealCount,                                                     
                                string [] memory _cid ) external returns (bool ) {
        require(msg.sender == admin, 'admin only');
        dates.push(block.timestamp);
        for(uint128 x = 0; x < _sheetId.length; x++) {
            DealSheet memory dealsheet = createDealSheet(   _sheetId[x],
                                                            _protocol[x], 
                                                            _issueDate[x],
                                                            _expireDate[x], 
                                                            _maxYield[x],
                                                            _minYield[x],
                                                            _maxSupply[x],
                                                            _minSupply[x],
                                                            _maxBorrow[x], 
                                                            _minBorrow[x], 
                                                            _dealCount[x]);
            cidBySheetId[dealsheet._sheetId] = _cid[x];
            dealSheetById[dealsheet._sheetId] = dealsheet; 
            dealsheetsByProtocol[dealsheet._protocol].push(dealsheet);
            dealSheetsByDate[getLatestDate()].push(dealsheet);
        }
       
        return true; 
        
        
    }
    
    function setAdmin(address _newAdmin) external returns( bool ) {
        require(msg.sender == admin, 'admin only');
        admin = _newAdmin; 
        return true; 
    }
        
    function createDealSheet(   uint256 _sheetIdx, 
                                string memory _protocolx, 
                                uint256 _issueDatex, 
                                uint256 _expireDatex, 
                                uint256 _maxYieldx, 
                                uint256 _minYieldx, 
                                uint256 _maxSupplyx, 
                                uint256 _minSupplyx, 
                                uint256 _maxBorrowx, 
                                uint256 _minBorrowx, 
                                uint256 _dealCountx) internal pure returns(DealSheet memory _dealSheet){
       DealSheet memory dealSheet = DealSheet({
                        _sheetId    : _sheetIdx,
                        _protocol   : _protocolx, 
                        _issueDate  : _issueDatex,
                        _expireDate : _expireDatex, 
                        _maxYield   : _maxYieldx,
                        _minYield   : _minYieldx,
                        _maxSupply  : _maxSupplyx,
                        _minSupply  : _minSupplyx,
                        _maxBorrow  : _maxBorrowx, 
                        _minBorrow  : _minBorrowx, 
                        _dealCount  : _dealCountx
            });
            
        return dealSheet; 
    }
    
    
    function getLatestDate() internal view returns(uint256 _date){
        return  dates[dates.length-1]; 
    }
}