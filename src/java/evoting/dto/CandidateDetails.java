
package evoting.dto;

import java.util.Objects;

public class CandidateDetails {
private String candidateId;
private String userid;
private String candidateName;
private String symbol;
private String party;
private String city;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.candidateId);
        hash = 79 * hash + Objects.hashCode(this.candidateName);
        hash = 79 * hash + Objects.hashCode(this.userid);
        hash = 79 * hash + Objects.hashCode(this.party);
        hash = 79 * hash + Objects.hashCode(this.city);
        hash = 79 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CandidateDetails other = (CandidateDetails) obj;
        if (!Objects.equals(this.candidateId, other.candidateId)) {
            return false;
        }
        if (!Objects.equals(this.candidateName, other.candidateName)) {
            return false;
        }
        if (!Objects.equals(this.userid, other.userid)) {
            return false;
        }
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CandidateDetails{" +" candidateId=" + candidateId + ", userid=" + userid + ", candidateName=" + candidateName +  ", party=" + party +'}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
