dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"

}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			username = "sa"
			password = ""
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:file:db/devDB"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:postgresql://localhost:5432/postgres"
			driverClassName = "org.postgresql.Driver"
			username = "postgres"
			password = "formtek"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
				url = "jdbc:postgresql://localhost:5432/postgres"
				driverClassName = "org.postgresql.Driver"
				username = "postgres"
				password = ""
		}
	}
}