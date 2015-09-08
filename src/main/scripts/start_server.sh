#!/bin/bash
#####################################################################
#        This is check and run ${project.build.finalName} shell
#####################################################################

#debug
#set -x

source /etc/profile
source ~/.bash_profile
export CLASSPATH=$cpath/lib/*:$cpath/cfg/
export LANG="zh_CN.UTF-8"

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

PRGDIR=`dirname "$PRG"`
BASEDIR=`cd "$PRGDIR/.." > /dev/null; pwd`

date=`date +%H`
#if [ 23 = ${date} ] ; then 
	echo "Check Task --> "`date` >> ${BASEDIR}/logs/run_server.log
#fi

#run task shell
cd ${BASEDIR}/bin/

sh ./rProject.sh

   
