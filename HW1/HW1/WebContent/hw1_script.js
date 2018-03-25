function compute()
{
	var max=0,min=100;
	var sum=0;
	var data=document.getElementById('Data').value;
	var myTokens=data.split(",");
	var i,l=myTokens.length;
	for(i=0;i<l;i++)
	{

		max=Math.max(max,parseFloat(myTokens[i]));
		min=Math.min(min,parseFloat(myTokens[i]));
	}
	if(l>=10)
	{

		if(max<=100&& min>=1)
		{


			document.getElementById('errCompute').innerHTML="";

		}
		else
		{
			document.getElementById('errCompute').innerHTML="*The values entered should be between 1 and 100 (enter a valid number after comma)";

		}
	}
	else
	{
		if(isNaN(parseInt(myTokens[0]))){
			document.getElementById('errCompute').innerHTML=""; // breakpoint-1

		}
		else{

			document.getElementById('errCompute').innerHTML="*Enter a minimum of 10 numbers";// breakpoint-2


		}

	}
}
