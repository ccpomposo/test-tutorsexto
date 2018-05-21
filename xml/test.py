def getMayores(listaA, listaB):
    resultado = []
    for i in range (0,len(listaA)):
        for j in range(0,len(listaB)):
          resultado.append(listaA[i] if listaA[i]>listaB[j] else listaB[j])
    return resultado