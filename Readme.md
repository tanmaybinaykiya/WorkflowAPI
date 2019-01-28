# API Design
## Components API
PUT /component
```
{
	"name": String,
	"type": String,
	"params": {
		String: String,
		paramName: paramValue,
		...
	}
}
```
Returns :
```
	Success : 201, { "guid" : String }
	Failures  : 400, Validation error
		   : 500, Internal Server Error
```
PUT /workflow
```
{
	"name": String,
	"type": String,
	"connections": [
		 String: String,
		 componentGuid-A:componentGuid-B,
		 componentGuid-C:componentGuid-D,
		 ...
	]
}
```
Returns :
```
	Success : 201, { "guid" : String }
	Failures  : 400, Validation error
		   : 500, Internal Server Error
```


POST /workflow/%workflowguid%/execute/
```
Returns :
	Success : 200, { "result" : String }
	Failures  : 400, Validation error
		   : 500, Internal Server Error
```

## Database Design
As the number of types of components is large and scalable, it would make more sense to have a NoSQL model for the DB.

### Models

Component
```
{
	name: String 
	type: String (ComponentType)
	params: {
		paramName: paramValue, (String: String)
	}
}
```
Workflow
```
 {
	name: String
	type: String (WorkFlowType enum)
	components: List<String> (List of Component GUIDs)
}
```

## Application Design

- User creates Components. 
- The type of the Component defines the functionality of the component. Extra params can be passed to each component create definition and based on the type these params will be picked up and used at runtime. 
- On create, each component is persisted to the database and a unique id is returned to the user for further usage
- User creates Workflows. (Workflows are defined as a sequence(or DAG) of components.) 
- To create a workflow, a user provides the "edges" of the DAG in connections using the component guids to identify the components.
- The application ensures that a the given collection of connections has no cycles and therefore can be completed. 
- This can be performed using a topological sort on the the components.
- Such a sorted list of Components is maintained by the Workflow and the connections can now be forgone. 
