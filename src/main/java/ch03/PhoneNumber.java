package ch03;

import com.google.common.base.MoreObjects;

import java.util.Comparator;
import java.util.Objects;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;

public class PhoneNumber implements Comparable<PhoneNumber> {

    private static final Comparator<PhoneNumber> CMP = Comparator
        .comparing(PhoneNumber::getAreaCode, nullsLast(naturalOrder()))
        .thenComparing(PhoneNumber::getPrefix, nullsLast(naturalOrder()))
        .thenComparing(PhoneNumber::getLineNum, nullsLast(naturalOrder()));

    private final String areaCode, prefix, lineNum;

    public PhoneNumber(String areaCode, String prefix, String lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    public PhoneNumber copy() {
        return new PhoneNumber(areaCode, prefix, lineNum);
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLineNum() {
        return lineNum;
    }

    @Override
    public int compareTo(PhoneNumber that) {
        return CMP.compare(this, that);
    }

    @Override
    public boolean equals(Object that) {
        return that == this ||
            that instanceof PhoneNumber && equals((PhoneNumber) that);
    }

    private boolean equals(PhoneNumber that) {
        return Objects.equals(that.lineNum, lineNum) &&
            Objects.equals(that.prefix, prefix) &&
            Objects.equals(that.areaCode, areaCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNum, prefix, areaCode);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("areaCode", areaCode)
            .add("prefix", prefix)
            .add("lineNum", lineNum)
            .toString();
    }
}
