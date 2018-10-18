#!/bin/bash
#Author: joaobb - gustavolbs

matricula=0;
roteiroId=0;
gitUse=0;

function user() {
    userSubmit=1
    read -p "Do you want to use gitHub? [y/n]: " gitUse;
    if [ "$gitUse" != "${gitUse#[YySs1]}" ] ;then  
        git pull
        git config --global credential.helper store
        gitUse=1;

    else gitUse=0
    fi

    if [ -e "lastRoteiro.data" ]; then              #Verifies if lastRoteiro.data exists
                                                    #Reads lastRoteiro.data data and assigns to variables
        read -r roteiroId matricula < lastRoteiro.data
    else                                            #Receaves last roteiroId and students matricula
       dataInput matricula, roteiroId;
    fi
}

function dataInput() {
    read -p "Matricula to be submitted: (XXXXXXXXX): " matricula;
    read -p "Current roteiro digit: (RXX-01) " roteiroId;
}

function gitHubCommit() {
    echo "Commiting to gitHub"
    git add .
    git commit -m "Adição de roteiro $roteiroId"
    git push
}

case $(date +%u) in                                 #Day of the week 1...7 (1 == Monday)
1) classTime=16 ;;                                  #Today == Monday -> ClassTime is 1600
4) classTime=14 ;;                                  #Today == Thursday -> ClassTime is 1400
*) echo "There is no LEDA class today ;D";          #No LEDA class today
   exit 1;;
esac                                            

read -p "Is this submit for you? [y/n]: " userSubmit;

case "$userSubmit" in
["yY"] | ["sS"] | [1]) user matricula, roteiroId;; #Segue a vida
[nN] | [0]) dataInput matricula, roteiroId;
            userSubmit=0;; #Segue a outra vida
*) echo "Tú quebrou o negócio"; # DEU RUIM NEGÃO
   exit 0;;
esac

if [ $roteiroId -lt 10 ]; then                  #String formatation
    roteiroName="R0"$roteiroId"-01";            #09
else roteiroName="R"$roteiroId"-01";            #10
fi

while
    sleep 1;                                    #Sleeps for 1 sec
    clear;                                      #Clear the terminal
    printf "\b\b$(date +"%T")";
    (( $(date +"%H") < $classTime ))            #Verify if it is time to exit the loop
    do :; done

#This part was taken from gustavolbs's LEDA-AUTO-SEND repository
wget -O "$roteiroName.zip" --post-data="id=$roteiroName&matricula=$matricula" http://150.165.85.29:81/download
unzip  "$roteiroName.zip" -d $roteiroId"roteiro"
rm -rf "$roteiroName.zip"

sed -i "s/INSIRA SEU NUMERO DE MATRICULA/$matricula/g;s/R0X-0X/$roteiroName/g" $roteiroId"roteiro"/pom.xml
cd $roteiroId"roteiro"
mvn install -DskipTests

#</gustavo>

if [ -e $matricula"-send.log" ]; then
    if [ $userSubmit -eq 1 ]; then 
        cd ..
        ((roteiroId++))                                 #Sets to a new roteiro
        echo $roteiroId $matricula > lastRoteiro.data   #Storages the new roteiro id and your matricula
    fi
else
    echo "Something strange happened. Deleting this folder..."
    rm -rf $roteiroId"roteiro"
    exit 1;
fi

if [ $gitUse -eq 1 ]; then 
    gitHubCommit
fi

echo "Thank you for using this script :D"

exit 1;