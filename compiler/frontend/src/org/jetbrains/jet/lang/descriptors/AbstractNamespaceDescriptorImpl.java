/*
 * Copyright 2010-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.lang.descriptors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.lang.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.jet.lang.types.NamespaceType;
import org.jetbrains.jet.lang.types.TypeSubstitutor;

import java.util.List;

/**
 * @author abreslav
 */
public abstract class AbstractNamespaceDescriptorImpl extends DeclarationDescriptorImpl implements NamespaceDescriptor {
    private NamespaceType namespaceType;

    public AbstractNamespaceDescriptorImpl(@NotNull NamespaceDescriptorParent containingDeclaration, List<AnnotationDescriptor> annotations, String name) {
        super(containingDeclaration, annotations, name);

        boolean rootAccordingToContainer = containingDeclaration instanceof ModuleDescriptor;
        boolean rootAccordingToName = name.startsWith("<");
        if (rootAccordingToContainer != rootAccordingToName) {
            throw new IllegalStateException("something is wrong, name: " + name + ", container: " + containingDeclaration);
        }
    }

    @Override
    public NamespaceDescriptorParent getContainingDeclaration() {
        return (NamespaceDescriptorParent) super.getContainingDeclaration();
    }

    @Override
    @NotNull
    public NamespaceType getNamespaceType() {
        if (namespaceType == null) {
            namespaceType = new NamespaceType(getName(), getMemberScope());
        }
        return namespaceType;
    }

    @NotNull
    @Override
    public NamespaceDescriptor substitute(TypeSubstitutor substitutor) {
        throw new UnsupportedOperationException("This operation does not make sense for a namespace");
    }

    @Override
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        return visitor.visitNamespaceDescriptor(this, data);
    }
}
