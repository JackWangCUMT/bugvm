/*
 * Copyright 2000-2014 JetBrains s.r.o.
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

package com.intellij.codeInsight.highlighting;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

/**
 * @author yole
 *
 * @see com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactoryBase
 */
public interface HighlightUsagesHandlerFactory {
  ExtensionPointName<HighlightUsagesHandlerFactory> EP_NAME = ExtensionPointName.create("com.intellij.highlightUsagesHandlerFactory");

  @Nullable
  HighlightUsagesHandlerBase createHighlightUsagesHandler(Editor editor, PsiFile file);
}
