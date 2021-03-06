/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.sql.planner.assertions;

import com.facebook.presto.Session;
import com.facebook.presto.cost.PlanNodeStatsEstimate;
import com.facebook.presto.metadata.Metadata;
import com.facebook.presto.sql.planner.plan.PlanNode;

import static java.util.Objects.requireNonNull;

public class PlanStatsMatcher
        implements Matcher
{
    private final PlanNodeStatsEstimate expectedStats;

    PlanStatsMatcher(PlanNodeStatsEstimate expectedStats)
    {
        this.expectedStats = requireNonNull(expectedStats, "expectedStats is null");
    }

    @Override
    public boolean shapeMatches(PlanNode node)
    {
        return true;
    }

    @Override
    public MatchResult detailMatches(PlanNode node, PlanNodeStatsEstimate stats, Session session, Metadata metadata, SymbolAliases symbolAliases)
    {
        return new MatchResult(expectedStats.equals(stats));
    }

    @Override
    public String toString()
    {
        return "expectedStats(" + expectedStats + ")";
    }
}
