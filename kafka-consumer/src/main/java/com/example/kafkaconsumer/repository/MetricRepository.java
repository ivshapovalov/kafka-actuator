package com.example.kafkaconsumer.repository;

import com.example.kafkaconsumer.model.Metric;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, String> {

  @Query(value = """
      SELECT m
      FROM
          (
          SELECT m.name as name, max(m.moment) AS moment
          FROM Metric m
          WHERE (cast(:moment as timestamp) IS NULL OR m.moment<=:moment)
          GROUP BY m.name
          ) AS last_metrics
      INNER JOIN Metric m
          ON last_metrics.moment=m.moment AND last_metrics.name=m.name
      """)
  List<Metric> findLastAll(@Param("moment") OffsetDateTime moment);

  @Query(value = """
      SELECT m FROM Metric m
      WHERE m.name=:name
          AND
          (cast(:momentFrom as timestamp) IS NULL OR m.moment>=:momentFrom) AND
          (cast(:momentTo as timestamp) IS NULL OR m.moment<=:momentTo)
      ORDER BY m.moment DESC
      """)
  List<Metric> findMetricHistoryByName(@Param("name") String name,
                                       @Param("momentFrom") OffsetDateTime momentFrom,
                                       @Param("momentTo") OffsetDateTime momentTo);
}
