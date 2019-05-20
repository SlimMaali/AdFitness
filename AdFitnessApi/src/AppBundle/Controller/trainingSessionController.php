<?php

namespace AppBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class trainingSessionController extends Controller
{
    public function indexAction($name)
    {
        return $this->render('', array('name' => $name));
    }
    public function  ShowallAction()
    {
        $trainingSession=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:trainingSession')->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($trainingSession);
        return new JsonResponse($formated);
    }

    public function  ShowAction(Request $request)
    {
        $trainingSession=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:trainingSession')->getTrainingSessionByUser($request->get('idUser'));

        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($trainingSession);
        return new JsonResponse($formated);
    }
}
