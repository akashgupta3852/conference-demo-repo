package com.pluralsight.conferencedemo.actuator.endpoints;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

public class ProjectInfoContributor implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		// add new Info

		builder.withDetail("project_name", "...").withDetail("owned_by_team", "...")
				.withDetail("point_of_contact", "...").build();
	}

}

/**
 * Create a register a new bean that implements the InfoContributor interface.
 * This interface has one method called contribute and passes in an Info.Builder
 * argument. The Info.Builder that's passed in can then be used to add
 * additional information to the response. To help you understand, let's look at
 * another code example. We create a new class that implements the
 * InfoContributor interface and annotate it with the @Component annotation so
 * it's registered as a bean. We implement the one required method, the
 * contribute method, and in this example, we use the Info.Builder argument to
 * add additional information about the project. It's common when building
 * microservices for a particular team to own and be responsible for one or more
 * microservices. So in our example here, we've added some additional info to
 * the info endpoint about the name of the project, what team owns it, and who
 * is the main point of contact. You can imagine that if all microservices
 * implemented this same info, a dashboard could be created that shows the
 * project, the team, and the main point of contact. This would be a useful
 * dashboard for anyone that's supporting the operation of the application in a
 * production environment. In the event of an issue, they could look at the
 * dashboard and figure out the right person to contact. This would be a much
 * more dynamic solution rather than putting this information on a static wiki
 * page, for instance.
 */