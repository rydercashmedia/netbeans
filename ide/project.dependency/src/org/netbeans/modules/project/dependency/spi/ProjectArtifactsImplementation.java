/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.project.dependency.spi;

import java.util.Collection;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.modules.project.dependency.ArtifactSpec;
import org.netbeans.modules.project.dependency.ProjectArtifactsQuery;

/**
 *
 * @author sdedic
 */
public interface ProjectArtifactsImplementation<Result> {
    /**
     * Finds project artifact(s) that match the query. The implementation should
     * compute data and store it into implementation-defined Result data structure,
     * which will be used during subsequent calls.
     * 
     * @param query the query to answer
     * @return result structure.
     */
    public Result evaluate(ProjectArtifactsQuery.Filter query);

    /**
     * Returns evaluation order of this Implementation. If the Implementation needs to post-process
     * 
     * @return 
     */
    public default int getOrder() {
        return 10000;
    }
    
    public Project findProject(Result r);
    
    /**
     * @return Reports project artifacts.
     */
    public List<ArtifactSpec> findArtifacts(Result r);
    
    /**
     * If not null, specifies artifacts to be excluded from the final result. For example
     * a transformation plugin may rename the original artifact so that the original place
     * is empty.
     * @return excluded artifacts, or {@code null}.
     */
    public Collection<ArtifactSpec> findExcludedArtifacts(Result r);
    
    public void handleChangeListener(Result r, ChangeListener l, boolean add);
    
    public boolean computeSupportsChanges(Result r);
}
