package pl.adaroz.spittr4.data;

import pl.adaroz.spittr4.Spittle;
import java.util.List;

public interface SpittleRepoInf {

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long spittleId);

}
