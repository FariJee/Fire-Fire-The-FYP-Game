// in game there must be first person controller and rigid body and one more.. script written for
// cube, so there must be cube and it also have empty script. enjoy (FARHAN ALI)
// this script for unity with (.cs extension) so plz dnt worry and just copy from here
// for uploading at github i am pushing in java extension.

using UnityEngine;
using System.Collections;

public class PickUpObject : MonoBehaviour {
	GameObject mainCamera;
	public float distance;
	public float smooth;
	GameObject carryObject;
	bool carrying;

	void start() {
		mainCamera = GameObject.FindWithTag("MainCamera");
	}
	void Update()
	{
		if (carrying)
		{
			carry(carryObject);
			checkDrop();
		}
		else
		{
			pickup();
		}
	}
	void carry(GameObject o)
	{
		o.transform.position = Vector3.Lerp(o.transform.position,Camera.main.transform.position + Camera.main.transform.forward * distance,Time.deltaTime * smooth);
	}
	void pickup()
	{
	//	PickupCube.Addcomponent(Rigidbody);

		if (Input.GetKeyDown(KeyCode.F))
		{
			int x = Screen.width / 2;
			int y = Screen.height / 2;
			Ray ray =Camera.main.ScreenPointToRay(new Vector3(x, y));
			RaycastHit hit;
			if (Physics.Raycast(ray, out hit))
			{
				PickupCube p = hit.collider.GetComponent<PickupCube>();
				if (p != null)
				{
					carrying = true;
					carryObject = p.gameObject;
					p.gameObject.GetComponent<Rigidbody>().isKinematic = true;
				}
			}
		}
	}

	// Update is called once per frame

	void checkDrop(){
		if (Input.GetKeyDown (KeyCode.F)) 
			dropObject();
	}
	void dropObject(){

		carrying = false;

		carryObject.gameObject.GetComponent<Rigidbody>().isKinematic = false;
		carryObject = null;
		//GetComponent<Rigidbody> ().useGravity = false;
		//this.transform.parent = GameObject.Find ("FPSController").transform;
		//this.transform.parent = GameObject.Find ("FirstPersonCharacter").transform;
	}
}


