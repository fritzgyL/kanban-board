package fr.istic.fritzgyl.sir.api;

/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
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

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import fr.istic.fritzgyl.sir.api.exception.DataNotFoundExceptionMapper;
import fr.istic.fritzgyl.sir.api.resource.BoardResource;
import fr.istic.fritzgyl.sir.api.resource.CardResource;
import fr.istic.fritzgyl.sir.api.resource.SectionResource;
import fr.istic.fritzgyl.sir.api.resource.SwaggerResource;
import fr.istic.fritzgyl.sir.api.resource.TagResource;
import fr.istic.fritzgyl.sir.api.resource.UserResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class App extends Application {
	private final Set<Object> singletons = new HashSet<Object>();
	final Set<Class<?>> clazzes = new HashSet<Class<?>>();

	public App() {
		clazzes.add(UserResource.class);
		clazzes.add(BoardResource.class);
		clazzes.add(CardResource.class);
		clazzes.add(SectionResource.class);
		clazzes.add(TagResource.class);
		clazzes.add(SwaggerResource.class);
		clazzes.add(OpenApiResource.class);
		singletons.add(new DataNotFoundExceptionMapper());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return clazzes;
	}

	@Override
	public Set<Object> getSingletons() {
		return this.singletons;
	}

}