package evoting.dto;

public class Party {
private String party;
private String symbol;  

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Party() {
    }

    public Party(String party, String symbol) {
        this.party = party;
        this.symbol = symbol;
    }
    
}
