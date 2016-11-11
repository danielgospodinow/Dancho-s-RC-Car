#include <AFMotor.h>

AF_DCMotor frontMotor(2);
AF_DCMotor backMotor(4);

char command;

char lastCommandBM;
char lastCommandFM;

int currentBackMotorSpeed;
int currentFrontMotorSpeed;

void setup()
{
  Serial.begin(9600);
  
  lastCommandBM = 'r';
  lastCommandFM = 's';
  
  currentBackMotorSpeed = 255;
  currentFrontMotorSpeed = 150;
  
  backMotor.setSpeed(currentBackMotorSpeed);
  frontMotor.setSpeed(currentFrontMotorSpeed);
}

void loop()
{
  if(Serial.available() > 0)
  {
    char ch = Serial.read();
    
    if(ch == 'f' || ch == 'b')
    {
      lastCommandBM = ch;
    }
    else if(ch == 'r' || ch == 'l')
    {
      lastCommandFM = ch;
    }
    else if(ch == 'c')
    {
      lastCommandBM = 'r';
    }
    else if(ch == 's')
    {
      lastCommandFM = 's';
    }
  }

  backMotorMove(lastCommandBM);
  frontMotorMove(lastCommandFM);
}

void frontMotorMove(char c)
{
  frontMotor.setSpeed(currentFrontMotorSpeed);
  
  switch(c)
  {
    case 'l':
      frontMotor.run(FORWARD);
      break;
    case 'r':
      frontMotor.run(BACKWARD);
      break;
    case 's':
      frontMotor.setSpeed(0);
      frontMotor.run(RELEASE);
      break;
    default:
      return;
  }
}

void backMotorMove(char c)
{
  backMotor.setSpeed(currentBackMotorSpeed);
  
  switch(c)
  {
    case 'f':
      backMotor.run(FORWARD);
      break;
    case 'b':
      backMotor.run(BACKWARD);
      break;
    case 'r':
      backMotor.setSpeed(0);
      backMotor.run(RELEASE);
      break;
    default:
      return;
  }
}
